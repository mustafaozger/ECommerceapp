package com.example.e_commerceapp.Pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginPageBinding
import com.example.e_commerceapp.repo.CartPageDAORepository
import com.example.e_commerceapp.repo.ProfileDAORepository
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPage : Fragment() {
    lateinit var binding:FragmentLoginPageBinding
    lateinit var profileViewModel:ProfilePageViewModel
    lateinit var cartPage:CartPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            val tempCart:CartPageViewModel by viewModels()
            cartPage= tempCart
            val tempProfilePage:ProfilePageViewModel by viewModels()
            profileViewModel=tempProfilePage


        }catch (e:Exception){
            Log.e("hatam",e.message.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            binding=FragmentLoginPageBinding.inflate(layoutInflater)




        binding.signInButton.setOnClickListener{
            profileViewModel.login(requireContext(),binding.loginEmail.text.toString(),binding.loginPassword.text.toString(), isSuccess = {isSuccess->
                if (isSuccess){
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginPage_to_mainPage)
                }
            }, message = {
                Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            })
        }

        binding.textView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginPage_to_registryPage)
        }

//            profileViewModel.isLogin(requireContext()){
//                if (it){
//                    Log.d("hatamLogin","itWork")
//                    if(view!=null){
//                        Navigation.findNavController(requireView()).navigate(R.id.action_loginPage_to_mainPage)
//                    }else{
//                        Log.d("hatamLogin","view null")
//
//                    }
//                }else{
//                    binding.layoutProgressbar.visibility=View.GONE
//                    binding.layoutLoginPage.visibility=View.VISIBLE
//                }
//            }


            return binding.root

        }catch (e:Exception){
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility=View.GONE
    }

}