package com.example.e_commerceapp.repo

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductPageDAORepository {

    private var productList:MutableLiveData<ArrayList<Product>>

    init {
        productList= MutableLiveData()
        loadProductList()
    }

    fun getProductList():MutableLiveData<ArrayList<Product>>{
        return productList
    }

    fun loadProductList(){


        val returnedList=ArrayList<Product>()

        val db=FirebaseDatabase.getInstance()
        val ref=db.getReference("Products")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val product=i.getValue(Product::class.java)
                    product?.let { returnedList.add(it) }
                }
                productList.value=returnedList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}