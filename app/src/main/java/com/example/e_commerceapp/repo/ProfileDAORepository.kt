package com.example.e_commerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.Classes.User
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import java.util.UUID

class ProfileDAORepository {
    
    private var uid:String?=null

    fun getUID(): String? {
        return this.uid
    }

    fun setUId(uid:String){
        this.uid=uid
        UID=uid
    }

    fun createDatabase(){
        val db=Firebase.firestore
        val userCollection=db.collection("Users")
        val list=ArrayList<CartList>()
        val favList=ArrayList<String>()

        val userHasMap= hashMapOf(
            "user_uid" to uid,
            "cart_list" to list,
            "favorite_list" to favList
        )

        userCollection.document(uid!!).set(userHasMap)
    }

    companion object{
        var UID:String?=null//when user login , UID  change
    }



}