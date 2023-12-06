package com.example.e_commerceapp.repo

import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import java.util.EventListener


class CartPageDAORepository {




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
            val db=Firebase.firestore
            val dbRef=db.collection("Users")
            val user= uid?.let { dbRef.document(it) }
            if (user != null) {
                user.get().addOnSuccessListener { documentSnapshot->
                    if(documentSnapshot.exists()){
                        val userData = documentSnapshot.data
                        if (userData != null) {
                            val newCartList = userData["cart_list"] as List<Map<String, Any>>?
                            val retCartList=ArrayList<CartList>()
                            if (newCartList != null) {
                                for (item in newCartList) {
                                    val productID = item["product_id"] as String
                                    val cartCount = (item["cart_count"] as Long).toInt() // Firestore'da cart_count Long türünde olabilir
                                    val prdouct =productVM.getProductWithId(productID)
                                    retCartList.add(CartList(prdouct,cartCount))
                                }
                                cartList.value=retCartList
                            }
                        }
                    }
                }
            }


        }catch (e:Exception){
            Log.e("hatamFirebaseGetList",e.toString())
        }
    }
    fun addCart(product_id:String,count:Int){
        val uid=ProfileDAORepository.UID
        val db=Firebase.firestore
        val dbRef=db.collection("Users")
        val user= uid?.let { dbRef.document(it) }

        try {
            if (user != null) {
                user.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        val newCart_list = userData?.get("cart_list") as ArrayList<HashMap<String, Any>>
                        val cartItem = newCart_list.find { it["product_id"] == product_id }

                        if (cartItem != null) {
                            val currentCount = cartItem["cart_count"] as Int
                            val updatedCount = currentCount + 1
                            cartItem["cart_count"] = updatedCount
                        } else {
                            val newCartItem = hashMapOf<String, Any>(
                                "product_id" to product_id,
                                "cart_count" to count
                            )
                            newCart_list.add(newCartItem)
                            Log.d("cartObje",newCartItem.toString()+"addCart")
                        }
                        user.update("cart_list", newCart_list)

                    } else {
                        //User not exists
                        Log.e("hatam","User not exists")
                    }

                }.addOnFailureListener {
                    Log.d("hatamFirebaseHata", it.toString())
                }
            }
        }catch (e:Exception){
            Log.e("hatamFirebaseHatam","loadProductList:  "+ e.toString())
        }
    }




    fun changeCartProductCount(product_id:String,changeAmount:Int){
        val uid=ProfileDAORepository.UID
        val db=Firebase.firestore
        val dbRef=db.collection("Users")
        val user= uid?.let { dbRef.document(it) }
        try {
            if (user != null) {
                user.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        val cart_list = userData?.get("cart_list") as ArrayList<HashMap<String, Any>>
                        val cartItem = cart_list.find { it["product_id"] == product_id }
                        if (cartItem != null) {
                            val currentCount = (cartItem["cart_count"] as Long).toInt()
                            val updatedCount = currentCount + changeAmount
                            cartItem["cart_count"] = updatedCount
                            user.update("cart_list", cart_list)
                        }

                    }

                }.addOnFailureListener {
                    Log.d("hatamFirebaseHata", it.toString())
                }
            }
        }catch (e:Exception){
            Log.e("hatamFirebaseHatam","changeCartProduct :  "+e.toString())
        }
    }


    fun removeProductFromCart(productVM: ProductPageViewModel, productIdToRemove: String) {
        val uid = ProfileDAORepository.UID
        val db = Firebase.firestore
        val dbRef = db.collection("Users")
        val user = uid?.let { dbRef.document(it) }
        try {
            if (user != null) {
                user.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userData = documentSnapshot.data
                        if (userData != null) {
                            val newCartList = userData["cart_list"] as? ArrayList<Map<String, Any>>?

                            if (newCartList != null) {
                                // Belirli ürünü çıkar
                                newCartList.removeAll { it["product_id"] == productIdToRemove }

                                // Güncellenmiş listeyi Firestore'a geri yükle
                                val updatedData = mapOf("cart_list" to newCartList)
                                user.update(updatedData)
                                    .addOnSuccessListener {
                                        Log.d("hatamFirestoreRemoveProduct", "Ürün başarıyla sepetten çıkarıldı.")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("hatamFirestoreRemoveProduct", "Ürünü çıkarma hatası: $e")
                                    }

                                // LiveData'ya güncellenmiş listeyi atayın
                                val retCartList = ArrayList<CartList>()
                                for (item in newCartList) {
                                    val productID = item["product_id"] as String
                                    val cartCount = (item["cart_count"] as Long).toInt()
                                    val product = productVM.getProductWithId(productID)
                                    retCartList.add(CartList(product, cartCount))
                                }
                                cartList.value = retCartList
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("hatamFirebaseRemoveProduct", e.toString())
        }
    }
}
