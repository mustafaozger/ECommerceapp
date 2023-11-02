package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.Classes.Banners
import com.example.e_commerceapp.Classes.Campaigns
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.repo.MainPageDAORepository

class MainPageViewModel() :ViewModel() {

    lateinit var productDao:MainPageDAORepository
    init {
        productDao= MainPageDAORepository()
    }

    fun getBanner():MutableLiveData<ArrayList<Banners>>{
        return productDao.bannerMutableList
    }

    fun getCampaign():MutableLiveData<ArrayList<Campaigns>>{
        return productDao.campaignMutableList
    }
    fun getCategories():MutableLiveData<ArrayList<Categories>>{
        return productDao.categoriesMutableList
    }


}