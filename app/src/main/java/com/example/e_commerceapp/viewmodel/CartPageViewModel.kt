package com.example.e_commerceapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.repo.CartPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartPageViewModel @Inject constructor(@SuppressLint("StaticFieldLeak") private var cartPageDAORepository: CartPageDAORepository) :ViewModel(){
//    private var cartPageDAORepository: CartPageDAORepository= CartPageDAORepository()

    fun getCartList(productVM:ProductPageViewModel):MutableLiveData<ArrayList<CartList>?>{
        return cartPageDAORepository.getList(productVM)
    }    fun addCart(product_id:String,count:Int,profilePageViewModel:ProfilePageViewModel){
        cartPageDAORepository.addCart(product_id,count,profilePageViewModel)
    }

}