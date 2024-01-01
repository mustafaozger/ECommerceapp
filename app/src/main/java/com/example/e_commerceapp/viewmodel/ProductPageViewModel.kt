package com.example.e_commerceapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.repo.ProductPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductPageViewModel  @Inject constructor(private var productDao:ProductPageDAORepository):ViewModel() {
    private val _productList = MutableLiveData<ArrayList<Product>>()


    fun getProductList():  MutableLiveData<ArrayList<Product>> {
       return productDao.getProductList()
    }

    // Add a function to sort the product list based on a sorting mode


    fun getProductWithId(product_id: String):Product{
       return productDao.getProductWithId(product_id)
    }

    }