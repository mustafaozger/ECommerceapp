package com.example.e_commerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.Pages.CartPage
import com.example.e_commerceapp.Pages.FavoritiesPage
import com.example.e_commerceapp.Pages.MainPage
import com.example.e_commerceapp.Pages.ProfilePage
import com.example.e_commerceapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var  bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

           binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
           bottomNavigationView = findViewById(R.id.bottomNavigationView) as BottomNavigationView
           bottomNavigationView.setOnItemSelectedListener {
               when (it.itemId) {
                   R.id.bottomHome -> {
                       loadFragment(MainPage())
                       return@setOnItemSelectedListener true
                   }

                   R.id.bottomFavorite -> {
                       loadFragment(FavoritiesPage())
                       return@setOnItemSelectedListener true
                   }

                   R.id.bottomCart -> {
                       loadFragment(CartPage())
                       return@setOnItemSelectedListener true
                   }

                   R.id.bottomProfile -> {
                       loadFragment(ProfilePage())
                       return@setOnItemSelectedListener true
                   }

                   else -> {
                       return@setOnItemSelectedListener true
                   }
               }
           }





    }


    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }


}