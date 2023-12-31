package com.example.e_commerceapp.Pages

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.e_commerceapp.Classes.FilterOptions
import com.example.e_commerceapp.Classes.Product
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.ProductPageAdapter
import com.example.e_commerceapp.databinding.FragmentProductPageBinding
import com.example.e_commerceapp.viewmodel.FavoritiesPageViewModel
import com.example.e_commerceapp.viewmodel.ProductPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductPage : Fragment(),SearchView.OnQueryTextListener{
    lateinit var binding:FragmentProductPageBinding
    lateinit var productPageViewModel:ProductPageViewModel
    lateinit var favoritiesPageViewModel: FavoritiesPageViewModel
    private var sortingMode=MutableLiveData<Int>()
    private val filterOptions=MutableLiveData<FilterOptions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempProductpage:ProductPageViewModel by viewModels()
        productPageViewModel=tempProductpage
        sortingMode.value=0
        filterOptions.value=FilterOptions(null,null,false)
        val tempFavoritiesPageViewModel:FavoritiesPageViewModel by viewModels()
        favoritiesPageViewModel=tempFavoritiesPageViewModel
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductPageBinding.inflate(layoutInflater,container,false)


        binding.searchBar.setOnQueryTextListener(this)

        sortingMode.observe(viewLifecycleOwner){sortingMode->
            productPageViewModel.getProductList(sortingMode).observe(viewLifecycleOwner){productlist->
                favoritiesPageViewModel.getFavoriteList().observe(viewLifecycleOwner){favoirteList->
                 filterOptions.observe(viewLifecycleOwner){filters->
                     val adapter = ProductPageAdapter(requireContext(), sortProducts(sortingMode,filter(filters,productlist)) ,this,favoirteList)
                     adapter.notifyDataSetChanged()
                     binding.productPageRecycler.adapter = adapter
                     binding.productPageRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                 }

                }
            }
        }
        binding.btnFilter.setOnClickListener {
            showFilterDialog()
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



        filterOptions.observe(viewLifecycleOwner){
            freecargo.isChecked= it.freeCargo == true
            fastorder.isChecked= it.fastOrder == true
            newproduct.isChecked= it.newProduct == true

        }
            val temp=FilterOptions(freecargo.isChecked,fastorder.isChecked,newproduct.isChecked)
            freecargo.setOnCheckedChangeListener { _, isChecked ->
                temp.freeCargo = isChecked
                filterOptions.value=temp
            }

            fastorder.setOnCheckedChangeListener { _, isChecked ->
                temp.fastOrder = isChecked
                filterOptions.value=temp
            }

            newproduct.setOnCheckedChangeListener { _, isChecked ->
                temp.newProduct = isChecked
                filterOptions.value=temp
            }


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
    fun sortProducts(sortingMode: Int, productList:ArrayList<Product>) : List<Product> {
        when (sortingMode) {
            1 -> {
                return productList.sortedBy { it.product_price }
            }
            -1 -> {
                return productList.sortedByDescending { it.product_price }
            }else -> {
            productList
        }
        }
        return productList
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    fun filter(filterOptions: FilterOptions,list:ArrayList<Product> ):ArrayList<Product>{
        var filteredList=list
        if(filterOptions.freeCargo==true){
            filteredList= filteredList.filter { it.freecargo==true } as ArrayList<Product>
        }
        if(filterOptions.fastOrder==true){
            filteredList= filteredList.filter { it.fastorder==true } as ArrayList<Product>
        }
        if(filterOptions.newProduct==true){
            filteredList= filteredList.filter { it.newproduct==true } as ArrayList<Product>
        }
        return filteredList
    }



}