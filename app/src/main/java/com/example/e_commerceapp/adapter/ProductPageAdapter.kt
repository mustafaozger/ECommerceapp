package com.example.e_commerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R

import com.example.e_commerceapp.databinding.DesignProductListBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.FavoritiesPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.squareup.picasso.Picasso

class ProductPageAdapter(
    var context: Context,
    var productList: List<Product>,
    val fragment:Fragment,
    val favoriLis: ArrayList<FavoriteList>
):
    RecyclerView.Adapter<ProductPageAdapter.ProductPageVH>() {
    inner class ProductPageVH(var binding: DesignProductListBinding):RecyclerView.ViewHolder(binding.root) {
        lateinit var productPageViewModel:ProductPageViewModel
        lateinit var cartPageViewModel: CartPageViewModel
        lateinit var favoritiesPageViewModel: FavoritiesPageViewModel
        init {
            val tempCart:CartPageViewModel by fragment.viewModels()
            val tempProduct:ProductPageViewModel by fragment.viewModels()
            val tempFavorite:FavoritiesPageViewModel by fragment.viewModels()
            cartPageViewModel =tempCart
            productPageViewModel=tempProduct
            favoritiesPageViewModel=tempFavorite
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

        Picasso.get().load(product.product_image).resize(450    ,450)
            .placeholder(R.drawable.product_loading_photo)
            .into(binding.imageView2)

        binding.productPageProductName.setText(product.product_name+ " " + product.product_info)
        binding.productPagePrice.setText(product.product_price.toString())

        binding.addCartButton.setOnClickListener {
            Toast.makeText(context,"Sepete Eklendi",Toast.LENGTH_LONG).show()
            product.product_id?.let { it1 -> holder.cartPageViewModel.addCart(it1,1) }
        }


        checkIsFavorite(product.product_id.toString(),favoriLis)

        val isFavorite:Boolean=holder.favoritiesPageViewModel.checkIsFavorite(product.product_id.toString())



        if(isFavorite==true){
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        }
        binding.favoriteButton.setOnClickListener{
            if(isFavorite==true || binding.favoriteButton.drawable==R.drawable.baseline_favorite_24.toDrawable()) {

                binding.favoriteButton.setImageResource(R.drawable.outline_favorite_border_24)
                holder.favoritiesPageViewModel.removeFromFavoriteList(product.product_id.toString())
                Toast.makeText(context,"Favorilerden Çıkarıldı",Toast.LENGTH_LONG).show()
            }else{
                binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
                holder.favoritiesPageViewModel.addFavoriteList(product.product_id.toString(),holder.productPageViewModel)
                Toast.makeText(context,"Favorilere Eklendi",Toast.LENGTH_LONG).show()
            }
        }
    }

//
    fun checkIsFavorite(product_id:String, favoriteList: ArrayList<FavoriteList>):Boolean{
        val product= favoriteList?.find {
        it.favorite_product?.product_id ==product_id
    }
        return product !=null
    }


}