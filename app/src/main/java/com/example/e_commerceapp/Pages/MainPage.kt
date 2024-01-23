package com.example.e_commerceapp.Pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.e_commerceapp.Classes.Banners
import com.example.e_commerceapp.Classes.Campaigns
import com.example.e_commerceapp.Classes.Categories
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.MainPageBannerAdadpter
import com.example.e_commerceapp.adapter.MainPageCampaignLogoAdapter
import com.example.e_commerceapp.adapter.MainPageCategoryAdapter
import com.example.e_commerceapp.databinding.FragmentMainPageBinding
import com.example.e_commerceapp.databinding.MainPageBannerDesignBinding
import com.example.e_commerceapp.viewmodel.FavoritiesPageViewModel
import com.example.e_commerceapp.viewmodel.MainPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPage : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var mainPageViewModel:MainPageViewModel
    lateinit var binding: FragmentMainPageBinding
    lateinit var favoritiesPageViewModel: FavoritiesPageViewModel
    lateinit var productPageViewModel: ProductPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MainPageViewModel by viewModels()
        mainPageViewModel=tempViewModel
        val tempFavoritiesPageViewModel:FavoritiesPageViewModel by viewModels()
        favoritiesPageViewModel=tempFavoritiesPageViewModel
        val tempProduct:ProductPageViewModel by viewModels()
        productPageViewModel=tempProduct

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMainPageBinding.inflate(layoutInflater,container,false)

        binding.searchBar.setOnQueryTextListener(this)


        try {
            favoritiesPageViewModel.initFavoriteList(productPageViewModel)

              mainPageViewModel.getBanner().observe(viewLifecycleOwner) {
                  val bannerAdapter = MainPageBannerAdadpter(requireContext(), it)
                  binding.rcylBanner.adapter = bannerAdapter

            }
            mainPageViewModel.getCampaign().observe(viewLifecycleOwner) {
                val campaignAdapter = MainPageCampaignLogoAdapter(requireContext(), it)
                binding.rcylCatagoryImage.adapter = campaignAdapter

            }
            mainPageViewModel.getCategories().observe(viewLifecycleOwner) {
                val categoryAdapter = MainPageCategoryAdapter(requireContext(), it)
                binding.rcylCategoryName.adapter = categoryAdapter

            }

            binding.rcylCategoryName.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.rcylBanner.layoutManager = LinearLayoutManager(requireContext())
            binding.rcylCatagoryImage.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)



        }catch (e:Exception){
            Log.e("hatam",e.toString())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility=View.VISIBLE
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val bundle=Bundle()
        bundle.putString("mainPageSearcQuery",query)
        Navigation.findNavController(requireView()).navigate(R.id.action_mainPage_to_productPage,bundle)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}