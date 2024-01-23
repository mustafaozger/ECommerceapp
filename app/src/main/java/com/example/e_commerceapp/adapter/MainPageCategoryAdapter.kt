package com.example.e_commerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentMainPageBinding
import com.example.e_commerceapp.databinding.MainCategorynameDesignBinding

class MainPageCategoryAdapter(var context:Context,var categoryNameList:List<Categories>):RecyclerView.Adapter<MainPageCategoryAdapter.MainPageCategoryAdapterVH>() {
    inner class MainPageCategoryAdapterVH(var binding:MainCategorynameDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageCategoryAdapterVH {

        val layoutmanager=LayoutInflater.from(context)
        val binding=MainCategorynameDesignBinding.inflate(layoutmanager)
        return MainPageCategoryAdapterVH(binding)
    }

    override fun getItemCount(): Int {
        return categoryNameList.size
    }

    override fun onBindViewHolder(holder: MainPageCategoryAdapterVH, position: Int) {
        val binding=holder.binding
        binding.text.setText(categoryNameList.get(position).category_name)
        binding.text.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_mainPage_to_productPage)
        }
    }
}