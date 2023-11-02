package com.example.e_commerceapp.repo

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R

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
        val product1=Product("zagori",21,null, R.drawable.formatwebp)
        val product2=Product("SADasd",21,null, R.drawable.formatwebp_2)
        val product3=Product("XIAOMI",21,null, R.drawable.formatwebp_3)
        val product4=Product("AVVA",21,null, R.drawable.formatwebp_4)
        val product5=Product("sadad",21,null, R.drawable.formatwebp_5)
        val product6=Product("sdadadsvfa",21,null, R.drawable.formatwebp_7)
        val product7=Product("zagori",21,null, R.drawable.formatwebp_8)
        val product8=Product("tgfdv",21,null, R.drawable.formatwebp_8)
        val product9=Product("fdsfds",21,null, R.drawable.formatwebp_8)

        val list=ArrayList<Product>()
        list.add(product1)
        list.add(product2)
        list.add(product3)
        list.add(product4)
        list.add(product5)
        list.add(product6)
        list.add(product7)
        list.add(product8)
        list.add(product9)

        productList.value=list


    }


}