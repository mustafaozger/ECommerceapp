package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var user_uid:String?=null
                ,var user_cart_list:ArrayList<CartList>?=null
                ,var user_favorite_list:ArrayList<String>?=null) {
}