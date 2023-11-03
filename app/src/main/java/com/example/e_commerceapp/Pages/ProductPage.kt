package com.example.e_commerceapp.Pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.ProductPageAdapter
import com.example.e_commerceapp.databinding.FragmentProductPageBinding
import com.example.e_commerceapp.viewmodel.ProductPageViewModel


class ProductPage : Fragment() {
    private lateinit var spinnerPriceSortList:ArrayList<String>
    private lateinit var spinnerPriceSortAdapter:ArrayAdapter<String>

    private lateinit var spinnerFilterAdapter:ArrayAdapter<String>

    lateinit var binding:FragmentProductPageBinding
    lateinit var productPageViewModel:ProductPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:ProductPageViewModel by viewModels()
        productPageViewModel=temp
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductPageBinding.inflate(layoutInflater,container,false)

        productPageViewModel.getProductList().observe(viewLifecycleOwner) {

            Log.d("hatam",it.toString()+"dsa")
            val adapter = ProductPageAdapter(requireContext(), it, resources )
            binding.productPageRecycler.adapter = adapter
        }

        spinnerPriceSortList=getSortSpinnerList()
        spinnerPriceSortAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,spinnerPriceSortList)
        binding.spinnerSort.adapter=spinnerPriceSortAdapter

        spinnerFilterAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,getSpinnerFilterList())
        binding.spinnerFilter.adapter=spinnerFilterAdapter



        binding.productPageRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        return binding.root
    }

    fun getSortSpinnerList():ArrayList<String>{
        val spinnerList=ArrayList<String>()
        spinnerList.add("Sırala")
        spinnerList.add("En Düşük Fiyat")
        spinnerList.add("En Yüksek Fiyat")
        spinnerList.add("Çok Satanlar")
        return spinnerList
    }

    fun getSpinnerFilterList():ArrayList<String>{
        val spinnerList=ArrayList<String>(

        )
        spinnerList.add("Filtrele")


        return spinnerList
    }


}