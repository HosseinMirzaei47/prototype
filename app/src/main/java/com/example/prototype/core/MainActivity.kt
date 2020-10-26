package com.example.prototype.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.prototype.R
import com.example.prototype.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        /*val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_login,
                R.id.navigation_second,
                R.id.navigation_users
            )
        )

        val navController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
        navView.setupWithNavController(navController)*/
    }
}