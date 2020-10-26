package com.example.prototype.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.prototype.core.storage.data.Settings
import com.example.prototype.features.users.ui.UsersFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentsFactory @Inject constructor(
    private val settings: Settings
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            UsersFragment::class.java.name -> {
                UsersFragment()
            }
            else -> return super.instantiate(classLoader, className)
        }
    }

}