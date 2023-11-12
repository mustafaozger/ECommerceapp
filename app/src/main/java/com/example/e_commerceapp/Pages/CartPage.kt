package com.example.e_commerceapp.Pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.CartPageAdapter
import com.example.e_commerceapp.databinding.FragmentCartPageBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel


class CartPage : Fragment() {

    lateinit var binding:FragmentCartPageBinding
    lateinit var cartPageViewModel: CartPageViewModel
    lateinit var profilePage: ProfilePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartPageBinding.inflate(layoutInflater,container,false)
        val temp: CartPageViewModel by viewModels()
        binding.cartRcycler.layoutManager=LinearLayoutManager(requireContext())

        cartPageViewModel=temp
        Log.d("cartList","val"+cartPageViewModel.getCartList().value.toString())
        cartPageViewModel.getCartList().observe(viewLifecycleOwner){
            Log.d("cartList",it.toString())
            if(it!=null){
                val adapter=CartPageAdapter(requireContext(),it)
                binding.cartRcycler.adapter=adapter
            }

        }


        // Inflate the layout for this fragment
        return binding.root
    }


}