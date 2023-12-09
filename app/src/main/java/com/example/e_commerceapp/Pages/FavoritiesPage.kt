package com.example.e_commerceapp.Pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Classes.FavoriteList
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.FavoritePageAdapter
import com.example.e_commerceapp.databinding.FragmentFavoritiesPageBinding
import com.example.e_commerceapp.databinding.FragmentProfilePageBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.FavoritiesPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritiesPage : Fragment() {
    lateinit var binding:FragmentFavoritiesPageBinding
    lateinit var favoritiesPageViewModel:FavoritiesPageViewModel
    lateinit var cartPageViewModel: CartPageViewModel
    lateinit var productPageViewModel: ProductPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempVM:FavoritiesPageViewModel by viewModels()
        favoritiesPageViewModel=tempVM
        val tempCartVM:CartPageViewModel by viewModels()
        cartPageViewModel=tempCartVM
        val tempProdct:ProductPageViewModel by viewModels()
        productPageViewModel=tempProdct
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoritiesPageViewModel.initFavoriteList(productPageViewModel)

        // Inflate the layout for this fragment
        favoritiesPageViewModel.getFavoriteList().observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = FavoritePageAdapter(requireContext(), it,favoritiesPageViewModel,cartPageViewModel,productPageViewModel)
                binding.recyler.adapter=adapter
            }
        }

        binding=FragmentFavoritiesPageBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}