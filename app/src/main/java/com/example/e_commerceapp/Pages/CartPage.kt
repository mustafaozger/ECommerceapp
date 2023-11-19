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
import com.example.e_commerceapp.CompanionClasses.TotalPrice
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.CartPageAdapter
import com.example.e_commerceapp.databinding.FragmentCartPageBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartPage : Fragment() {

    lateinit var binding:FragmentCartPageBinding
    lateinit var cartPageViewModel: CartPageViewModel
    lateinit var profilePage: ProfilePageViewModel
    lateinit var productPage:ProductPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp: CartPageViewModel by viewModels()
        cartPageViewModel=temp

        val tempProfil:ProfilePageViewModel by viewModels()
        profilePage=tempProfil

        val tempProduct:ProductPageViewModel by viewModels()
        productPage=tempProduct

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartPageBinding.inflate(layoutInflater,container,false)

        binding.cartRcycler.layoutManager=LinearLayoutManager(requireContext())

        Log.d("cartList","val"+cartPageViewModel.getCartList(productPage).value.toString())
        cartPageViewModel.getCartList(productPage).observe(viewLifecycleOwner){
            Log.d("cartList",it.toString())
            if(it!=null){
                val adapter=CartPageAdapter(requireContext(),it)
                binding.cartRcycler.adapter=adapter
                binding.cartRcycler.adapter?.notifyDataSetChanged()
                binding.textView.setText(CartPageAdapter.tot.toString())
            }

        }




        // Inflate the layout for this fragment
        return binding.root
    }


}