package com.example.prototype

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.prototype.databinding.ActivityMainBinding
import com.example.prototype.features.auth.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navigateToProperDestination()
    }

    private fun navigateToProperDestination() {
        lifecycleScope.launch {
            userViewModel.getToken()?.collect { token ->
                if (token.isNotNullOrEmpty()) changeGraph()
            }
        }
    }

    private fun changeGraph() {
        val navHostFragment = mainNavHost as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.auth_graph)
        graph.startDestination = R.id.navigation_dashboard
        navHostFragment.navController.graph = graph
    }

    private fun String.isNotNullOrEmpty() = this.isEmpty().not()

}