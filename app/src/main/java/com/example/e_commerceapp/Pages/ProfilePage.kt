package com.example.e_commerceapp.Pages

import android.content.Intent
import android.net.Uri
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            Navigation.findNavController(it).navigate(R.id.action_profilePage_to_loginPage)
        }

        binding.layoutCustomerService.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/+905558825243")
            intent.setPackage("com.android.chrome")
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            } else {
                // Chrome yüklü değilse, genel bir tarayıcı intent'i başlat
                val genericIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/+905558825243"))
                startActivity(genericIntent)
            }
        }

        return binding.root
    }

}