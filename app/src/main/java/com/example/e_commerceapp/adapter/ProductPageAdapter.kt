package com.example.e_commerceapp.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R

import com.example.e_commerceapp.databinding.DesignProductListBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.squareup.picasso.Picasso
import kotlin.math.log

class ProductPageAdapter(var context: Context,var productList: List<Product>):
    RecyclerView.Adapter<ProductPageAdapter.ProductPageVH>() {
    inner class ProductPageVH(var binding: DesignProductListBinding):RecyclerView.ViewHolder(binding.root)

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
        val cartPageViewModel=CartPageViewModel()
        product.product_image?.let { Log.d("hatamUrl", it) }
        Picasso.get().load(product.product_image).resize(450,450)
            .placeholder(R.drawable.product_loading_photo)
            .into(binding.imageView2)

        binding.productPageProductName.setText(product.product_name+ " " + product.product_info)
        binding.productPagePrice.setText(product.product_price.toString())

        binding.addCartButton.setOnClickListener {
            product.product_id?.let { it1 -> cartPageViewModel.addCart(it1,1) }
        }
    }

//

}