package com.example.e_commerceapp.repo

import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.Classes.User
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.EventListener


class CartPageDAORepository {


    private val db=Firebase.firestore

    private var cartList=MutableLiveData<ArrayList<CartList>?>()


    fun getList(productVM:ProductPageViewModel): MutableLiveData<ArrayList<CartList>?> {

        runBlocking {
            getListFromDatabase(productVM)
        }
        return cartList
    }
    private fun getListFromDatabase( productVM:ProductPageViewModel ){
        try {
            val uid=ProfileDAORepository.UID
            Log.d("cartList","first list has"+ cartList.value.toString())

            if (uid != null) {
                Log.d("cartList","uid has"+ uid.toString())

                db.collection("Users").document(uid).get().addOnSuccessListener { documentSnapshot->
                    Log.d("cartList","documentSnapshot has"+ documentSnapshot.data.toString())

                    if(documentSnapshot.exists()){
                        val userData = documentSnapshot.data

                        if (userData != null) {
                            Log.d("cartList","userData:   "+userData["cart_list"].toString())
                            val newCartList = userData["cart_list"] as List<Map<String, Any>>?
                            val retCartList=ArrayList<CartList>()
                            if (newCartList != null) {
                                for (item in newCartList) {
                                    val productID = item["product_id"] as String
                                    val cartCount = (item["cart_count"] as Long).toInt() // Firestore'da cart_count Long türünde olabilir
                                    val prdouct =productVM.getProductWithId(productID)

                                    retCartList.add(CartList(prdouct,cartCount))
                                    Log.d("cartList", "product:   $prdouct")

                                }
                                cartList.value=retCartList
                                Log.d("cartList","cartList: "+cartList.value.toString())
                            }
                        }



//                        val newCartList = documentSnapshot.toObject<HashMap<String, Any>>()?.get("cart_list") as ArrayList<HashMap<String, Any>>?
//                        Log.d("cartList","newCartList has"+ newCartList.toString())
//
//                        if(newCartList!=null){
//                            val retList=ArrayList<CartList>()
//                            for (item in newCartList){
//                                val product_id=item["product_id"] as String
//                                val product_count=item["cart_count"] as Int
//                                val product=productPageViewModel.getProductWithId(product_id)
//                                retList.add(CartList(product,product_count))
//                                Log.d("cartList","item has"+ item.toString())
//
//                            }
//                            cartList.value=retList
//                        }
                    }
                }
            }
            Log.d("cartList","end list has"+ cartList.value.toString())

        }catch (e:Exception){
        }
    }
    fun addCart(product_id:String,count:Int,profilePageViewModel:ProfilePageViewModel){
        try {
            val uid=profilePageViewModel.getUid()
            val userRef = db.collection("Users").document(uid!!)
            userRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userData = documentSnapshot.data
                    val cart_list = userData?.get("cart_list") as ArrayList<HashMap<String, Any>>
                    var cartItem = cart_list.find { it["product_id"] == product_id }


                    if (cartItem != null) {
                        val currentCount = cartItem["cart_count"] as Int
                        val updatedCount = currentCount + 1
                        cartItem["cart_count"] = updatedCount
                    } else {
                        val newCartItem = hashMapOf<String, Any>(
                            "product_id" to product_id,
                            "cart_count" to count
                        )
                        cart_list.add(newCartItem)
                        Log.d("cartObje",newCartItem.toString()+"addCart")
                    }
                    userRef.update("cart_list", cart_list)

                } else {
                    //User not exists
                    Log.e("cartObje","not exists")
                }

            }.addOnFailureListener {
                Log.d("firebaseHata", it.toString())
            }
        }catch (e:Exception){
            Log.e("firebaseHatam","loadProductList:  "+ e.toString())
        }
    }




    fun changeCartProductCount(uid:String,product_id:String,changeAmount:Int){

        try {
            val dbRef = db.collection("Users")
            val user = dbRef.document(uid)



            user.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userData = documentSnapshot.data
                    val cart_list = userData?.get("cart_list") as ArrayList<HashMap<String, Any>>

                    var cartItem = cart_list.find { it["product_id"] == product_id }

                    if (cartItem != null) {
                        val currentCount = cartItem["cart_count"] as Int
                        val updatedCount = currentCount + changeAmount
                        cartItem["cart_count"] = updatedCount
                        user.update("cart_list", cart_list)
                    }
                }

            }.addOnFailureListener {
                Log.d("firebaseHata", it.toString())

            }
        }catch (e:Exception){
            Log.e("firebaseHatam","changeCartProduct :  "+e.toString())
        }
    }
}