package com.example.e_commerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.FavoriteList
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class FavoritePageDAORepository  {

    private val db=Firebase.firestore
    private var favoriteList:MutableLiveData<ArrayList<FavoriteList>> = MutableLiveData<ArrayList<FavoriteList>>()
    val uid=ProfileDAORepository.UID


    fun getFavoriteList(productPageDAORepository: ProductPageDAORepository):MutableLiveData<ArrayList<FavoriteList>>{
        initFavoriteList(productPageDAORepository)
        return this.favoriteList
    }

    fun addToFavoriteList(product_id: String){
        addFavoriteList(product_id)
    }

    fun removeFavoriteList(product_id: String){
        removeFromeFavoriteList(product_id)
    }

    private fun initFavoriteList(productPageDAORepository:ProductPageDAORepository){
        if(uid!=null){
            db.collection("Users").document(uid).get().addOnSuccessListener { documentSnapshot->
                if(documentSnapshot.exists()){
                    val userData=documentSnapshot.data
                    if (userData!=null){
                        val newFavoriteList=userData["favorite_list"] as List<Map<String,Any>>?
                        val retFavoriteList=ArrayList<FavoriteList>()
                        if(newFavoriteList!=null){
                            for(item in newFavoriteList){
                                val productId=item.get("product_id") as String
                                val product=productPageDAORepository.getProductWithId(productId)
                                retFavoriteList.add(FavoriteList(product))
                            }
                            favoriteList.value=retFavoriteList
                        }
                    }
                }
            }
        }
    }

    private fun addFavoriteList(product_id:String){
        if(uid!=null){
            val userRef= db.collection("Users").document(uid )
            userRef.get().addOnSuccessListener { documentSnapshot->
                if(documentSnapshot.exists()){
                    val data=documentSnapshot.data
                    if (data!=null){
                        val favoriteList= data.get("favorite_list") as ArrayList<Map<String,Any>>
                        val newProduct= hashMapOf<String,Any>(
                            "product_id" to product_id
                        )
                        favoriteList.add(newProduct)
                        userRef.update("favorite_list",favoriteList)
                    }
                }
            }.addOnFailureListener {
                Log.e("DatabaseExceptions",it.toString())
            }
        }
    }

    private fun removeFromeFavoriteList(product_id:String){
        if(uid!=null){
            val userRef= db.collection("Users").document(uid )
            userRef.get().addOnSuccessListener { documentSnapshot->
                if(documentSnapshot.exists()){
                    val data=documentSnapshot.data
                    if (data!=null){
                        val favoriteList= data.get("favorite_list") as ArrayList<Map<String,Any>>
                        val newProduct= hashMapOf<String,Any>(
                            "product_id" to product_id
                        )
                        favoriteList.remove(newProduct)
                        userRef.update("favorite_list",favoriteList)
                    }
                }
            }.addOnFailureListener {
                Log.e("DatabaseExceptions",it.toString())
            }
        }
    }

}