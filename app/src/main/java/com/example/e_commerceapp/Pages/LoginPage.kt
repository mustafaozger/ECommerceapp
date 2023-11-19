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
    lateinit var auth:FirebaseAuth
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
            Log.e("sıcıs",e.message.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {


        binding=FragmentLoginPageBinding.inflate(layoutInflater)
        auth= FirebaseAuth.getInstance()



        binding.signInButton.setOnClickListener{

            if(binding.loginEmail.text.toString()!="" && binding.loginPassword.text.toString()!=""){
                    auth.signInWithEmailAndPassword(binding.loginEmail.text.toString(),binding.loginPassword.text.toString()) .addOnCompleteListener{
                    if(it.isSuccessful){
                        profileViewModel.setUid(auth.uid!!)
                        Navigation.findNavController(requireView()).navigate(R.id.action_loginPage_to_mainPage)
                    }else{
                        Toast.makeText(requireContext(),"E-mail or Password Wrong",Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(),"E-mail or Password Empty",Toast.LENGTH_LONG).show()

            }
        }

        binding.textView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginPage_to_registryPage)
        }
            return binding.root
        // Inflate the layout for this fragment

        }catch (e:Exception){
            Log.e("sıcısım","OnCreate"+e.toString())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility=View.GONE
    }

}