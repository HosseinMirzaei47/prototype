package com.example.prototype.features.auth.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.prototype.R
import com.example.prototype.core.resource.Status
import com.example.prototype.databinding.FragmentLoginBinding
import com.example.prototype.features.auth.data.AuthRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

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
        navigateToProperDestination()
        loginUser()
        onRegisterClick()
    }

    private fun navigateToProperDestination() {
        lifecycleScope.launch {
            loginViewModel.getToken()?.asLiveData()?.observe(viewLifecycleOwner, { token ->
                if (token.isNotEmpty()) {
                    findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationHome())
                } else {
                    binding.loginViewContainer.isVisible = true
                }
            })
        }
    }

    private fun onRegisterClick() {
        binding.loginRegisterButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToRegisterFragment())
        }
    }

    private fun loginUser() {
        binding.loginEnterButton.setOnClickListener {
            val email = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isValidEmail()) {
                loginViewModel.loginUser(
                    AuthRequest(email, password)
                )
            }

        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            if (it.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            }
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

}