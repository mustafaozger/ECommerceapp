package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.repo.FavoritePageDAORepository
import com.example.e_commerceapp.repo.ProductPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritiesPageViewModel @Inject constructor(private val favoritePageDAORepository: FavoritePageDAORepository,private val productPageDAORepository: ProductPageDAORepository):ViewModel(){
    fun getFavoriteList():MutableLiveData<ArrayList<FavoriteList>>{
        return favoritePageDAORepository.getFavoriteList()
    }
    fun addFavoriteList(productID:String,productPageViewModel: ProductPageViewModel){
        viewModelScope.launch {
            favoritePageDAORepository.addToFavoriteList(productID,productPageViewModel)
        }

    }
    fun removeFromFavoriteList(productID: String){
        viewModelScope.launch {
            favoritePageDAORepository.removeFavoriteList(productID)
        }
    }

    fun checkIsFavorite(product_id:String):Boolean {
        return favoritePageDAORepository.checkIsFavorite(product_id)
    }
    fun initFavoriteList(productPageViewModel: ProductPageViewModel){
        viewModelScope.launch {
            favoritePageDAORepository.initFavoriteList(productPageViewModel)
        }
    }
}