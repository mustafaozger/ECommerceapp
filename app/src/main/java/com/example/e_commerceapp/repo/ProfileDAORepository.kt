package com.example.e_commerceapp.repo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.e_commerceapp.Classes.CartList
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

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


    fun login(context:Context ,email:String,password:String,isSuccess:(Boolean)->Unit,message:(String)->Unit){
        Log.d("hatamProf","loginworlk")
        if(email!="" && password != ""){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    isSuccess(true)
                    message("Giriş Başarılı")
                    auth.uid?.let { it1 -> setUId(it1)
                        addUserToLocalDatabase(context,uid.toString())
                    }
                }else{
                    isSuccess(false)
                    message("E-posta yada Şifre Hatalı")
                }
            }
        }else{
            isSuccess(false)
            message("E-posta yada Şifre Boş")
        }

    }

    @SuppressLint("CommitPrefEdits")
    fun addUserToLocalDatabase(context: Context, uid:String){
        val sp= context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE)
        val editor=sp.edit()
        editor.putString("uid",uid)
        editor.apply()
    }


    fun isLogin(context: Context,callback :(Boolean)->Unit){
        val sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE)
        val uid=sp.getString("uid",null)
        if (uid!=null){
            setUId(uid)
            Log.d("hatamProf","uid "+uid.toString())
            callback(true)
        }else{
            callback(false)
        }

    }

    @SuppressLint("CommitPrefEdits")
    fun logOut(context: Context){
        val sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE)
        val editor=sp.edit()
        editor.remove("uid")

    }


}