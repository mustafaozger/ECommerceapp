package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.repo.ProfileDAORepository

class ProfilePageViewModel:ViewModel() {

    private val profileDAORepository=ProfileDAORepository()
    fun getUid():String?{
        return profileDAORepository.getUID()
    }
    fun setUid(newUid:String){
        profileDAORepository.setUId(newUid)
    }
    fun createDatabase(){
        profileDAORepository.createDatabase()
    }
}