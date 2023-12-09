package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.repo.ProfileDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor(private var profileDAORepository:ProfileDAORepository):ViewModel() {

//    private val profileDAORepository=ProfileDAORepository()
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