package com.example.e_commerceapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.databinding.DesignCartBinding

class CartPageAdapter(var context: Context,var cartList: ArrayList<CartList>,var resources: Resources):
    RecyclerView.Adapter<CartPageAdapter.CartPageVH>() {
    inner class CartPageVH(var bindng:DesignCartBinding):RecyclerView.ViewHolder(bindng.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartPageVH {
        val layoutInflater=LayoutInflater.from(context)
        val binding= DesignCartBinding.inflate(layoutInflater)
        return CartPageVH(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartPageVH, position: Int) {
        val binding=holder.bindng
        val product=cartList.get(position).product

        binding.cartProductCount.text=cartList.get(position).productCount.toString()
//        val image=reSizePhoto(product!!.product_imag!!)
//        binding.cartPrdctImage.setImageBitmap(image)
        if (product != null) {
            binding.cartTotalPrice.setText((cartList.get(position).productCount!! * product.product_price!!).toString())
        }
        if (product != null) {
            binding.cartPrductName.setText(product.product_name)
        }

        binding.cartAddProduct.setOnClickListener {

            binding.cartProductCount.text=(binding.cartProductCount.text.toString().toInt()+1).toString()
            if (product != null) {
                binding.cartTotalPrice.text=(product.product_price!! *binding.cartProductCount.text.toString().toInt()).toString()
            }

        }

        binding.cartMinusProdut.setOnClickListener {
            if(binding.cartProductCount.text.toString().toInt()>0){
                binding.cartProductCount.text=(binding.cartProductCount.text.toString().toInt()-1).toString()
                if (product != null) {
                    binding.cartTotalPrice.text=(product.product_price!! *binding.cartProductCount.text.toString().toInt()).toString()
                }
            }

        }

        binding.cartRemove.setOnClickListener {
            cartList.remove(cartList.get(position))
        }

    }

    private fun reSizePhoto(image:Int): Bitmap {
        val options= BitmapFactory.Options()
        options.inMutable=true
        val imageBitmap= BitmapFactory.decodeResource(resources, image,options)

        val newWidth = 450 // Yeni genişlik
        val newHeight = 450 // Yeni yükseklik
        val resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, newWidth, newHeight, true)
        return resizedBitmap
    }


}