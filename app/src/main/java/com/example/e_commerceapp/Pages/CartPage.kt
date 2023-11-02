package com.example.e_commerceapp.Pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.e_commerceapp.Classes.CartList
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.CartPageAdapter
import com.example.e_commerceapp.databinding.FragmentCartPageBinding


class CartPage : Fragment() {

    lateinit var binding:FragmentCartPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartPageBinding.inflate(layoutInflater,container,false)
        val product1= Product("zagori",21,null, R.drawable.formatwebp)
        val product2= Product("SADasd",21,null, R.drawable.formatwebp_2)
        val product3= Product("XIAOMI",21,null, R.drawable.formatwebp_3)
        val product4= Product("AVVA",21,null, R.drawable.formatwebp_4)
        val tempList=ArrayList<CartList>()
        tempList.add(CartList(product1,2))
        tempList.add(CartList(product2,4))
        tempList.add(CartList(product3,1))
        tempList.add(CartList(product4,7))

        val adapter=CartPageAdapter(requireContext(),tempList,resources)
        binding.cartRcycler.layoutManager=LinearLayoutManager(requireContext())
        binding.cartRcycler.adapter=adapter

        // Inflate the layout for this fragment
        return binding.root
    }


}