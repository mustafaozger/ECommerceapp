package com.example.e_commerceapp.repo

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.runBlocking

class ProductPageDAORepository {

    private var productList:MutableLiveData<ArrayList<Product>>
    val db=FirebaseDatabase.getInstance()


    init {
        productList= MutableLiveData()
        runBlocking {
            loadProductList()
        }
    }

    fun getProductList():MutableLiveData<ArrayList<Product>>{
        return productList
    }

    suspend fun loadProductList(){

        try {


            val returnedList = ArrayList<Product>()

            val ref = db.getReference("Products")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        for (i in snapshot.children) {
                            val product = i.getValue(Product::class.java)
                            product?.let { returnedList.add(it) }
                        }
                    }
                    productList.value = returnedList

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("firebaseHatam","loadProductList:  "+ error.toString())
                }

            })
        }catch (e:Exception){
            Log.e("firebaseHatam","loadProductList:  "+ e.toString())
        }

    }





        fun getProductWithId(product_id: String):Product{
            try {
                Log.d("yeniDa","co "+ productList.value.toString())
                if(productList.value!=null){
                    Log.d("yeniDa", "productLİst null değil")

                    val products :ArrayList<Product> = productList.value!!
                    val returnProduct=products.find { it.product_id==product_id }
                    if (returnProduct!=null){
                        return returnProduct
                    }
                }
            }catch (e:Exception){
                Log.e("firebaseHatam","loadProductList:  "+ e.toString())
            }
            return Product()
        }




}