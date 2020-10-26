package com.example.prototype.features.second.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.prototype.R
import kotlinx.android.synthetic.main.bottom_sheet.*

class SecondFragment : Fragment(R.layout.fragment_second) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navView.setupWithNavController(findNavController())
    }
}