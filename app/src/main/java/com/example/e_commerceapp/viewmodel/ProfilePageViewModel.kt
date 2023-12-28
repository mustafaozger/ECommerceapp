package com.example.e_commerceapp.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.repo.ProfileDAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class ProfilePageViewModel @Inject constructor(private var profileDAORepository:ProfileDAORepository):ViewModel() {

    fun setUid(newUid:String){
        profileDAORepository.setUId(newUid)
    }
    fun createDatabase(){
        profileDAORepository.createDatabase()



    }
    fun login(contex:Context,email:String,password:String,isSuccess:(Boolean)->Unit,message:(String)->Unit){
        profileDAORepository.login(contex,email,password,isSuccess={
            isSuccess(it)
        }, message = {
            message(it)
        })
    }
    @Synchronized
    fun isLogin(activity: Activity, callback: (Boolean)->Unit){
        profileDAORepository.isLogin(activity,callback)
    }

    fun logOut(context: Context){
        profileDAORepository.logOut(context)
    }



}