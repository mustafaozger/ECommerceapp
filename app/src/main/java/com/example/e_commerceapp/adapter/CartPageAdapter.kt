package com.example.e_commerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.CompanionClasses.TotalPrice
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.DesignCartBinding
import com.squareup.picasso.Picasso

class CartPageAdapter(var context: Context,var cartList: ArrayList<CartList>?):
    RecyclerView.Adapter<CartPageAdapter.CartPageVH>() {

    inner class CartPageVH(var bindng:DesignCartBinding):RecyclerView.ViewHolder(bindng.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartPageVH {
        val layoutInflater=LayoutInflater.from(context)
        val binding= DesignCartBinding.inflate(layoutInflater)
        return CartPageVH(binding)
    }

    override fun getItemCount(): Int {
        return cartList?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartPageVH, position: Int) {
        val binding=holder.bindng
        val product=cartList?.get(position)?.product

        binding.cartProductCount.text=cartList?.get(position)?.productCount.toString()

        if (product != null && cartList!=null) {
            val total_price = (cartList?.get(position)?.productCount!! * product.product_price!!)
            binding.cartTotalPrice.setText(total_price.toString())
            binding.cartPrductName.setText(product.product_name)

            Picasso.get().load(product.product_image)
                .resize(350,350)
                .into(binding.cartPrdctImage)
            Log.d("totalim",total_price.toString())
            TotalPrice.TOTALPRICE += total_price
            tot+=total_price
            //TODO total price i düzelt
            binding.cartAddProduct.setOnClickListener {

                binding.cartProductCount.text=(binding.cartProductCount.text.toString().toInt()+1).toString()

                binding.cartTotalPrice.text=(product.product_price!! *binding.cartProductCount.text.toString().toInt()).toString()


            }


            binding.cartMinusProdut.setOnClickListener {
                if(binding.cartProductCount.text.toString().toInt()>0){
                    binding.cartProductCount.text=(binding.cartProductCount.text.toString().toInt()-1).toString()
                    binding.cartTotalPrice.text=(product.product_price!! *binding.cartProductCount.text.toString().toInt()).toString()
                }

            }

            binding.cartRemove.setOnClickListener {
                cartList?.remove(cartList?.get(position))
            }



        }



    }
    companion object{
        var tot=0
    }




}