package com.example.prototype.features.auth.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prototype.R
import com.example.prototype.core.resource.Status
import com.example.prototype.databinding.FragmentRegisterBinding
import com.example.prototype.features.auth.data.AuthRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding = FragmentRegisterBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onRegisterClick()
        onLoginClicked()
    }

    private fun onRegisterClick() {
        binding.registerButton.setOnClickListener {
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val passwordConfirm = binding.registerPasswordConfirm.text.toString()

            if (email.isValidEmail()) {
                if (password == passwordConfirm) {
                    registerViewModel.registerUser(
                        AuthRequest(email, password)
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.passwordsMismatchError),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        registerViewModel.registerResult.observe(viewLifecycleOwner, { resource ->
            if (resource.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(RegisterFragmentDirections.actionNavigationRegisterToNavigationDashboard())
            } else if (resource.status == Status.ERROR) {
                Toast.makeText(requireContext(), "Registration Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onLoginClicked() {
        binding.loginButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun CharSequence?.isValidEmail(): Boolean {

        if (isNullOrEmpty()) {
            binding.registerEmail.requestFocus()
            binding.registerEmail.error = getString(R.string.enterEmail)
            return false
        }

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
            binding.registerEmail.requestFocus()
            binding.registerEmail.error = getString(R.string.wrongEmail)
            return false
        }

        return true
    }

}