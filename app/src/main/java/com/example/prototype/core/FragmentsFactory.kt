package com.example.prototype.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.prototype.core.storage.data.Settings
import com.example.prototype.features.home.ui.LoginFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentsFactory @Inject constructor(
    private val settings: Settings
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            LoginFragment::class.java.name -> {
                LoginFragment()
            }
            else -> return super.instantiate(classLoader, className)
        }
    }

}