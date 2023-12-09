package com.example.e_commerceapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.repo.CartPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartPageViewModel @Inject constructor(@SuppressLint("StaticFieldLeak") private var cartPageDAORepository: CartPageDAORepository) :ViewModel(){

    fun getCartList(productVM:ProductPageViewModel):MutableLiveData<ArrayList<CartList>?>{
        return cartPageDAORepository.getList(productVM)
    }
    fun changeCartProductCount(product_id:String,changeAmount:Int){
        viewModelScope.launch {
            cartPageDAORepository.changeCartProductCount(product_id,changeAmount)

        }
    }
    fun removeProductFromCart(productVM: ProductPageViewModel, productIdToRemove: String) {
        viewModelScope.launch {
            cartPageDAORepository.removeProductFromCart(productVM,productIdToRemove)
        }
    }

}