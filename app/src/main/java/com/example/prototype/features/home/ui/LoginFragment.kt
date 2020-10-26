package com.example.prototype.features.home.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.prototype.R
import com.example.prototype.core.resource.Status
import com.example.prototype.databinding.FragmentLoginBinding
import com.example.prototype.features.home.data.AuthRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    companion object {
        const val minUsernameLength = 7
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginUser()
    }

    private fun setUpBottomNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_second,
                R.id.navigation_users
            )
        )

        val navController = requireActivity().findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(
            requireActivity() as AppCompatActivity,
            navController,
            appBarConfiguration
        )
    }

    private fun loginUser() {
        binding.loginEnterButton.setOnClickListener {
            val email = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()
            loginViewModel.loginUser(
                AuthRequest(
                    "eve.holt@reqres.in",
                    "cityslicka"
                )
            )
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationHome())
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    println("jalil please wait... ")
                }
                Status.ERROR -> {
                    println("jalil something went wrong!!! ")
                }
            }
            binding.loginProgressBar.visibility = View.INVISIBLE
        })
    }

    private fun CharSequence?.isValidEmail(): Boolean {

        if (isNullOrEmpty()) {
            binding.loginUsername.requestFocus()
            binding.loginUsername.error = getString(R.string.enterEmail)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
            binding.loginUsername.requestFocus()
            binding.loginUsername.error = getString(R.string.wrongEmail)
            return false
        }

        return true
    }

    private fun CharSequence?.isValidUsername(): Boolean {

        if (this == null) {

            binding.loginUsername.requestFocus()
            binding.loginUsername.error = getString(R.string.enterUsername)
            return false

        }

        if (isNullOrEmpty()) {

            binding.loginUsername.requestFocus()
            binding.loginUsername.error = getString(R.string.enterUsername)
            return false

        } else if (binding.loginUsername.text!!.length > minUsernameLength
            && !Patterns.EMAIL_ADDRESS.matcher(this).matches()
        ) {

            return true

        } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {

            binding.loginUsername.requestFocus()
            binding.loginUsername.error = getString(R.string.infoFormatError)
            return false

        }

        binding.loginUsername.error = null
        return true
    }
}