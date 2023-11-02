package com.example.e_commerceapp.Classes

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Campaigns(
        var campaignId: String?=null
        ,var campaignName:String?=null
        ,var campaignImage:Int?=null) {
}