package com.example.e_commerceapp.repo

import androidx.lifecycle.MutableLiveData
import java.util.UUID

class ProfileDAORepository {
    
    private var uid:String?=null

    fun getUID(): String? {
        return this.uid
    }

    fun setUId(uid:String){
        this.uid=uid
    }
}