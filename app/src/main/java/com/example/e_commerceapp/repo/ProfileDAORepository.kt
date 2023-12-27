package com.example.e_commerceapp.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.Classes.User
import com.example.e_commerceapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import java.util.UUID

class ProfileDAORepository {


    val auth=Firebase.auth
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


    fun login(email:String,password:String,isSuccess:(Boolean)->Unit,message:(String)->Unit){

        if(email!="" && password != ""){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    isSuccess(true)
                    message("Giriş Başarılı")
                    auth.uid?.let { it1 -> setUId(it1) }
                }else{
                    isSuccess(false)
                    message("E-mail or Password Wrong")
                }
            }
        }else{
            isSuccess(false)
            message("E-mail or Password Empty")
        }

    }



}