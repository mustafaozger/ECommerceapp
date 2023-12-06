package com.example.e_commerceapp.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.Pages.ProfilePage
import com.example.e_commerceapp.R

import com.example.e_commerceapp.databinding.DesignProductListBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log
class ProductPageAdapter(var context: Context,var productList: List<Product> ,val fragment:Fragment):
    RecyclerView.Adapter<ProductPageAdapter.ProductPageVH>() {
    inner class ProductPageVH(var binding: DesignProductListBinding):RecyclerView.ViewHolder(binding.root) {
        lateinit var productPageViewModel:ProductPageViewModel
        lateinit var cartPageViewModel: CartPageViewModel
        lateinit var profilePageViewModel: ProfilePageViewModel

        init {
            val tempCart:CartPageViewModel by fragment.viewModels()
            val tempProfile:ProfilePageViewModel by fragment.viewModels()
            val tempProduct:ProductPageViewModel by fragment.viewModels()
            cartPageViewModel =tempCart
            profilePageViewModel=tempProfile
            productPageViewModel=tempProduct
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductPageVH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=DesignProductListBinding.inflate(layoutInflater)
        return ProductPageVH(binding)
    }

    override fun getItemCount(): Int {

        return productList.size
    }

    override fun onBindViewHolder(holder: ProductPageVH, position: Int) {
        val binding=holder.binding
        val product=productList.get(position)


        product.product_image?.let { Log.d("hatamUrl", it) }
        Picasso.get().load(product.product_image).resize(450    ,450)
            .placeholder(R.drawable.product_loading_photo)
            .into(binding.imageView2)

        binding.productPageProductName.setText(product.product_name+ " " + product.product_info)
        binding.productPagePrice.setText(product.product_price.toString())

        binding.addCartButton.setOnClickListener {
            Log.d("cartpage","eklendi")
            Toast.makeText(context,"Sepete Eklendi",Toast.LENGTH_LONG).show()
            product.product_id?.let { it1 -> holder.cartPageViewModel.addCart(it1,1) }
        }

        if(product.isFavorite==true){
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        }
    }

//

}