package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FavoriteList (var favorite_product_id:String?=null
                         ,var favorite_product_uid: String?=null){
}