package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Categories(
    var categorieID:String?=null
    ,var category_name:String?=null) {
}