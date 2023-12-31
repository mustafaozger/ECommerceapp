package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Product( var product_id:String?=null
                    ,var product_name:String?=null
                   ,var product_info:String?=null
                   ,var product_price:Int?=null,
                   var product_category:Categories?=null,
                   var product_image:String?=null,
                   var isFavorite:Boolean?=null,
                    var fastorder:Boolean?=null
                    ,var freecargo:Boolean?=null
                    ,var newproduct:Boolean?=null) {
}