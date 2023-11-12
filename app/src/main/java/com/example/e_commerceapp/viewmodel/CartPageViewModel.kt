package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.repo.CartPageDAORepository

class CartPageViewModel :ViewModel(){
    private var cartPageDAORepository: CartPageDAORepository= CartPageDAORepository()

    fun getCartList():MutableLiveData<ArrayList<CartList>?>{
        return cartPageDAORepository.getList()
    }    fun addCart(product_id:String,count:Int,){
        cartPageDAORepository.addCart(product_id,count)
    }

}