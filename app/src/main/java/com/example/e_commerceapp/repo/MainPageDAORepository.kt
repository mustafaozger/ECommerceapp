package com.example.e_commerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.Banners
import com.example.e_commerceapp.Classes.Campaigns
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.MainPageBannerAdadpter
import com.example.e_commerceapp.adapter.MainPageCampaignLogoAdapter
import com.example.e_commerceapp.adapter.MainPageCategoryAdapter

class MainPageDAORepository {
    var bannerMutableList:MutableLiveData<ArrayList<Banners>>
    var campaignMutableList:MutableLiveData<ArrayList<Campaigns>>
    var categoriesMutableList:MutableLiveData<ArrayList<Categories>>


    init {
        bannerMutableList= MutableLiveData()
        campaignMutableList= MutableLiveData()
        categoriesMutableList= MutableLiveData()
        loadBanner()
        loadCampaign()
        loadCategories()
    }


    fun loadBanner():MutableLiveData<ArrayList<Banners>>{
        val banner1=Banners("1",R.drawable.banner1)
        val banner2=Banners("1", R.drawable.banner3)
        val banner3=Banners("1",R.drawable.banner3)
        val banner4=Banners("1",R.drawable.banner4)
        val banner5=Banners("1",R.drawable.banner5)
        val banner6=Banners("1",R.drawable.banner6)
        val banner7=Banners("1",R.drawable.banner7)
        val banner8=Banners("1",R.drawable.banner8)
        val banner9=Banners("1",R.drawable.banner9)
        val banner10=Banners("1",R.drawable.banner10)
        val banner11=Banners("1",R.drawable.banner10)

        val bannerList=ArrayList<Banners>()
        bannerList.add(banner1)
        bannerList.add(banner2)
        bannerList.add(banner3)
        bannerList.add(banner4)
        bannerList.add(banner5)
        bannerList.add(banner6)
        bannerList.add(banner7)
        bannerList.add(banner8)
        bannerList.add(banner9)
        bannerList.add(banner10)

        bannerMutableList.value=bannerList
        Log.e("hatam",bannerMutableList.value.toString()+"sadas")
        return bannerMutableList



    }

    fun loadCampaign():MutableLiveData<ArrayList<Campaigns>>{
        val campaign1=Campaigns("1","Avva",R.drawable.avva)
        val campaign2=Campaigns("1","Altınyıldız",R.drawable.altinyildiz)
        val campaign3=Campaigns("1","Beymen ",R.drawable.beymen_club)
        val campaign4=Campaigns("1","Gece Kuşu", R.drawable.gece_kusu)

        val campaignsList=ArrayList<Campaigns>()
        campaignsList.add(campaign1)
        campaignsList.add(campaign2)
        campaignsList.add(campaign3)
        campaignsList.add(campaign4)
        campaignMutableList.value=campaignsList
        return campaignMutableList
    }

    fun loadCategories():MutableLiveData<ArrayList<Categories>>{

        val category1=Categories("1","Tüm Kampanyalar")
        val category2=Categories("1","Kadın")
        val category3=Categories("1","Erkek")
        val category4=Categories("1","Gece Kuşu")
        val category5=Categories("1","Duffy")
        val category6=Categories("1","Altınyıldız")
        val category7=Categories("1","Beymen")
        val category8=Categories("1","Avva")

        val categoryList=ArrayList<Categories>()
        categoryList.add(category1)
        categoryList.add(category2)
        categoryList.add(category3)
        categoryList.add(category4)
        categoryList.add(category5)
        categoryList.add(category6)
        categoryList.add(category7)
        categoryList.add(category8)

        categoriesMutableList.value=categoryList
        return categoriesMutableList
    }


}