package com.example.e_commerceapp.Pages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginPageBinding
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginPage : Fragment() {
    lateinit var binding:FragmentLoginPageBinding
    lateinit var auth:FirebaseAuth
    lateinit var profileViewModel:ProfilePageViewModel
    var cartPage=CartPageViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginPageBinding.inflate(layoutInflater)
        auth= FirebaseAuth.getInstance()
        profileViewModel= ProfilePageViewModel()


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

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView=requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility=View.GONE
    }

}