package com.example.e_commerceapp.Pages

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.ProductPageAdapter
import com.example.e_commerceapp.databinding.FragmentProductPageBinding
import com.example.e_commerceapp.viewmodel.FavoritiesPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductPage : Fragment(){
    private lateinit var spinnerPriceSortList:ArrayList<String>
    private lateinit var spinnerPriceSortAdapter:ArrayAdapter<String>
    private lateinit var spinnerFilterAdapter:ArrayAdapter<String>
    lateinit var binding:FragmentProductPageBinding
    lateinit var productPageViewModel:ProductPageViewModel
    lateinit var favoritiesPageViewModel: FavoritiesPageViewModel
    var sortingMode=MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempProductpage:ProductPageViewModel by viewModels()
        productPageViewModel=tempProductpage
        sortingMode.value=0
        val tempFavoritiesPageViewModel:FavoritiesPageViewModel by viewModels()
        favoritiesPageViewModel=tempFavoritiesPageViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductPageBinding.inflate(layoutInflater,container,false)

//        productPageViewModel.getProductList().observe(viewLifecycleOwner) {productlist->
//
//            favoritiesPageViewModel.getFavoriteList().observe(viewLifecycleOwner){favoirteList->
//
//                val adapter = ProductPageAdapter(requireContext(), productlist ,this,favoirteList)
//                binding.productPageRecycler.adapter = adapter
//
//            }
//        }

        sortingMode.observe(viewLifecycleOwner){sortingMode->
            productPageViewModel.getProductList(sortingMode).observe(viewLifecycleOwner){productlist->
                favoritiesPageViewModel.getFavoriteList().observe(viewLifecycleOwner){favoirteList->

                    val adapter = ProductPageAdapter(requireContext(), sortProducts(sortingMode,productlist) ,this,favoirteList)
                    binding.productPageRecycler.adapter = adapter
                    binding.productPageRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)


                }
            }
        }

        binding.btnOrder.setOnClickListener {
            showSortingDialog()
        }


        binding.productPageRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)


        return binding.root
    }
    fun showFilterDialog(){
        val dialog=Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet_filter)
        val freecargo:CheckBox=dialog.findViewById(R.id.btn_filter_freecargo)
        val fastorder:CheckBox=dialog.findViewById(R.id.btn_filter_fastorder)
        val newproduct:CheckBox=dialog.findViewById(R.id.btn_filter_newproduct)
        

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setGravity(Gravity.BOTTOM)


    }



    fun showSortingDialog(){
        val dialog=Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet_sort)
        val layout_increase:Button=dialog.findViewById(R.id.btn_btnshee_increase)
        val layout_decrease:Button=dialog.findViewById(R.id.btn_btnshee_decrease)

        layout_increase.setOnClickListener {

            changeSortingMode(1)
        }
        layout_decrease.setOnClickListener {
            changeSortingMode(-1)

        }
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)
        dialog.window?.setGravity(Gravity.BOTTOM)


    }
    fun changeSortingMode(sorted:Int){
        this.sortingMode.value=sorted
    }
    fun sortProducts(sortingMode: Int,_productList:ArrayList<Product>) : List<Product> {
        when (sortingMode) {
            1 -> {
                return _productList.sortedBy { it.product_price }
            }
            -1 -> {
                return _productList.sortedByDescending { it.product_price }
            }else -> {
            _productList// Default sorting or no sorting
        }
        }
        return _productList
    }


}