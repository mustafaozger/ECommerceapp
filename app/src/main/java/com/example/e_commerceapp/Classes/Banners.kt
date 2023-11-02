package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Banners(var banner_id:String?=null
                   ,var bannerLink:Int?=null){

    //TODO cahnge banner Link to String

}