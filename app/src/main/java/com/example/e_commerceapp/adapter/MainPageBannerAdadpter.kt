package com.example.e_commerceapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Banners
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.MainPageBannerDesignBinding

class MainPageBannerAdadpter(var context: Context,var bannerList: List<Banners>):
    RecyclerView.Adapter<MainPageBannerAdadpter.MainPageBannerVH
            >() {
    inner class MainPageBannerVH(var binding: MainPageBannerDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageBannerVH {
        val layoutInflater=LayoutInflater.from(context)
        val binding=MainPageBannerDesignBinding.inflate(layoutInflater)
        return MainPageBannerVH(binding)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: MainPageBannerVH, position: Int) {
        val binding=holder.binding

        binding.bannerImage
        binding.bannerImage.setImageResource(bannerList.get(position).bannerLink!!)
        binding.bannerImage.setOnClickListener {
            try {
                Navigation.findNavController(it).navigate(R.id.action_mainPage_to_productPage)

            }catch (e:Exception){
                Log.e("hatamMainAdapter",e.message.toString())
            }


        }

    }
}