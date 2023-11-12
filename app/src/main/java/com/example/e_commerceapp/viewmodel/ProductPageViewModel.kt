package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.repo.ProductPageDAORepository

class ProductPageViewModel:ViewModel() {
    val productDao=ProductPageDAORepository()

    fun getProductList():MutableLiveData<ArrayList<Product>>{
        return productDao.getProductList()
    }



}