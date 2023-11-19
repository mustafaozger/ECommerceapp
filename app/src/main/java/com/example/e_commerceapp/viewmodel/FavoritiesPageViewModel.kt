package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.repo.FavoritePageDAORepository
import com.example.e_commerceapp.repo.ProductPageDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritiesPageViewModel @Inject constructor(private val favoritePageDAORepository: FavoritePageDAORepository,private val productPageDAORepository: ProductPageDAORepository):ViewModel(){
    fun getFavoriteList():MutableLiveData<ArrayList<FavoriteList>>{
        return favoritePageDAORepository.getFavoriteList(productPageDAORepository)
    }
    fun addFavoriteList(productID:String){
        favoritePageDAORepository.addToFavoriteList(productID)
    }
    fun removeFromFavoriteList(productID: String){
        favoritePageDAORepository.addToFavoriteList(productID)
    }
}