package com.example.e_commerceapp.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.DesignCartBinding
import com.example.e_commerceapp.databinding.DesignProductListBinding

class ProductPageAdapter(var context: Context,var productList: List<Product>,var resources: Resources):
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
        val image=reSizePhoto(product.product_imag!!)
        binding.imageView2.setImageBitmap(image)
//        binding.imageView2.setImageResource(product.product_imag!!)
        binding.productPageProductName.setText(product.product_name)
        binding.productPagePrice.setText(product.product_price.toString())

        binding.addCartButton.setOnClickListener {


        }
    }


    private fun reSizePhoto(image:Int):Bitmap{
        val options= BitmapFactory.Options()
        options.inMutable=true
        val imageBitmap=BitmapFactory.decodeResource(resources, image,options)

        val newWidth = 450 // Yeni genişlik
        val newHeight = 450 // Yeni yükseklik
        val resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, newWidth, newHeight, true)
        return resizedBitmap
    }

}