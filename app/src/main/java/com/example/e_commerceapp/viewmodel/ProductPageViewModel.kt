package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.repo.ProductPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductPageViewModel  @Inject constructor(private var productDao:ProductPageDAORepository):ViewModel() {
//    val productDao=ProductPageDAORepository()

    fun getProductList():MutableLiveData<ArrayList<Product>>{
        return productDao.getProductList()
    }
    fun getProductWithId(product_id: String):Product{
        return productDao.getProductWithId(product_id)
    }


}