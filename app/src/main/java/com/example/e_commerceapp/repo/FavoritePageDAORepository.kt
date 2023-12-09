package com.example.e_commerceapp.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class FavoritePageDAORepository  {

    private val db=Firebase.firestore
    private var favoriteList:MutableLiveData<ArrayList<FavoriteList>> = MutableLiveData<ArrayList<FavoriteList>>()
    val uid=ProfileDAORepository.UID


    fun getFavoriteList():MutableLiveData<ArrayList<FavoriteList>>{
        return this.favoriteList
    }

    fun addToFavoriteList(product_id: String,productPageViewModel: ProductPageViewModel){
        addFavoriteList(product_id,productPageViewModel)
    }

    fun removeFavoriteList(product_id: String){
        removeFromeFavoriteList(product_id)
    }

     fun initFavoriteList(productPageViewModel: ProductPageViewModel){
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
                                val product=productPageViewModel.getProductWithId(productId)
                                retFavoriteList.add(FavoriteList(product))
                            }
                            favoriteList.value=retFavoriteList
                        }
                    }
                }
            }
        }
    }

    private fun addFavoriteList(product_id:String,productPageViewModel: ProductPageViewModel){
        if(uid!=null){
            val product=productPageViewModel.getProductWithId(product_id)
            favoriteList.value?.add(FavoriteList(product))

            //add database
            val userRef= db.collection("Users").document(uid )
            userRef.get().addOnSuccessListener { documentSnapshot->
                if(documentSnapshot.exists()){
                    val data=documentSnapshot.data
                    if (data!=null){
                        val tempfavoriteList= data.get("favorite_list") as ArrayList<Map<String,Any>>
                        val newProduct= hashMapOf<String,Any>(
                            "product_id" to product_id
                        )
                        tempfavoriteList.add(newProduct)
                        userRef.update("favorite_list",tempfavoriteList)
                    }
                }
            }.addOnFailureListener {
                Log.e("DatabaseExceptions",it.toString())
            }
        }
    }

    private fun removeFromeFavoriteList(product_id:String){
        if(uid!=null){
            //Remove from list
            favoriteList.value?.remove( favoriteList.value?.find {
                it.favorite_product?.product_id==product_id
            }).let {
                Log.d("hatamFavoriteAdapter","silindi")
            }

            //Remove from Database
            val userRef= db.collection("Users").document(uid )
            userRef.get().addOnSuccessListener { documentSnapshot->
                if(documentSnapshot.exists()){
                    val data=documentSnapshot.data
                    if (data!=null){
                        val newfavoriteList= data.get("favorite_list") as ArrayList<Map<String,Any>>
                        val newProduct= hashMapOf<String,Any>(
                            "product_id" to product_id
                        )
                        newfavoriteList.remove(newProduct)
                        userRef.update("favorite_list",newfavoriteList)
                    }
                }
            }.addOnFailureListener {
                Log.e("hatamFavoriteDAO",it.toString())
            }
        }
    }
    fun checkIsFavorite(product_id:String):Boolean{
       val product= favoriteList.value?.find {
            it.favorite_product?.product_id ==product_id
        }
        return product !=null
    }

}