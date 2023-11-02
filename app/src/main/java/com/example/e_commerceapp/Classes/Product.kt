package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Product(var product_name:String?=null
                   ,var product_price:Int?=null,
                   var product_category:Categories?=null,
                   var product_imag:Int?=null,
                   var isFavoite:Boolean?=null) {
}