package com.example.e_commerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityMainBinding
import com.example.e_commerceapp.databinding.DesignFavoritepageAdapterBinding

class FavoritePageAdapter (var context: Context,var favoriList: List<Product>):
    RecyclerView.Adapter<FavoritePageAdapter.FavoritePageVH>() {
    inner class FavoritePageVH(var binding: DesignFavoritepageAdapterBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePageVH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=DesignFavoritepageAdapterBinding.inflate(layoutInflater)
        return FavoritePageVH(binding)
    }

    override fun getItemCount(): Int {
        return favoriList.size
    }

    override fun onBindViewHolder(holder: FavoritePageVH, position: Int) {
        val binding=holder.binding
        val product=favoriList.get(position)
//        binding.productImage.setImageResource(product.product_imag!!)
        binding.productPrice.setText(product.product_price!!)
        binding.productName.setText(product.product_name)
        binding.buttonAddCart.setOnClickListener {
            addCart()
        }
        binding.buttonFavorite.setImageResource(R.drawable.baseline_favorite_24)

    }

    fun addCart(){
        //TODO
    }
}