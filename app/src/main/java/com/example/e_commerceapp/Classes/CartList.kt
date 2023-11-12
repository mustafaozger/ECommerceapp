package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class CartList(var product: Product?=null
                    ,var productCount:Int?=null) {
}