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
import com.example.e_commerceapp.databinding.FragmentRegistryPageBinding
import com.example.e_commerceapp.repo.CartPageDAORepository
import com.example.e_commerceapp.viewmodel.CartPageViewModel
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryPage : Fragment() {
    lateinit var auth: FirebaseAuth
    lateinit var binding:FragmentRegistryPageBinding
    lateinit var profilePageVM:ProfilePageViewModel
    lateinit var cartPageDAORepository:CartPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:CartPageViewModel by viewModels()
        cartPageDAORepository=temp
        val tempProfil:ProfilePageViewModel by viewModels()
        profilePageVM=tempProfil

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegistryPageBinding.inflate(layoutInflater,container,false)
        auth=FirebaseAuth.getInstance()




        binding.button.setOnClickListener {
            val email=binding.emailEt.text.toString()
            val password=binding.passET.text.toString()
            val confirmPassword=binding.confirmPassEt.text.toString()

            if(email!=""&& password!=""&& confirmPassword!=""){
                if(password==confirmPassword){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            Navigation.findNavController(requireView()).navigate(R.id.action_registryPage_to_loginPage)
                            profilePageVM.setUid(auth.uid.toString())
                            profilePageVM.createDatabase()
              //              cartPageDAORepository.getCartList(auth.uid.toString())
                        }else{
                            Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }


                }else{
                    Toast.makeText(requireContext(),"Passwords not match",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(requireContext(),"Fields are Empty",Toast.LENGTH_LONG).show()
            }


        }

        binding.textView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_registryPage_to_loginPage)
        }


        return binding.root
    }


}