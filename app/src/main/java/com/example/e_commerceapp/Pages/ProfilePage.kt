package com.example.e_commerceapp.Pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentProfilePageBinding
import com.example.e_commerceapp.viewmodel.ProfilePageViewModel

class ProfilePage : Fragment() {

    lateinit var binding:FragmentProfilePageBinding
    lateinit var profilePageViewModel: ProfilePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempVM:ProfilePageViewModel by viewModels()
        profilePageViewModel=tempVM

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfilePageBinding.inflate(layoutInflater)


        binding.layoutExit.setOnClickListener{
            profilePageViewModel.logOut(requireContext())
            Navigation.findNavController(it).navigate(R.id.action_mainPage_to_profilePage)
        }

        return binding.root
    }

}