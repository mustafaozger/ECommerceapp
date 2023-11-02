package com.example.e_commerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.Classes.Campaigns
import com.example.e_commerceapp.databinding.MainPageCampaignDesignBinding

class MainPageCampaignLogoAdapter (var context: Context, var compaignList:List<Campaigns>):
    RecyclerView.Adapter<MainPageCampaignLogoAdapter.MainPageCampaignLogoAdapterVH>() {
    inner class MainPageCampaignLogoAdapterVH(var binding:MainPageCampaignDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainPageCampaignLogoAdapterVH {

        val layoutInflater=LayoutInflater.from(context)
        val binding=MainPageCampaignDesignBinding.inflate(layoutInflater)
        return MainPageCampaignLogoAdapterVH(binding)
    }

    override fun getItemCount(): Int {
        return compaignList.size
    }

    override fun onBindViewHolder(holder: MainPageCampaignLogoAdapterVH, position: Int) {
        val binding=holder.binding
        binding.campaignImage.setImageResource(compaignList.get(position).campaignImage!!)
        binding.campaignName.setText(compaignList.get(position).campaignName)
    }
}