package com.example.prototype.features.home.ui

import androidx.fragment.app.Fragment
import com.example.prototype.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

/*@AndroidEntryPoint*/
@ExperimentalCoroutinesApi
class LoginFragment /*constructor(
    private val settings: Settings
)*/ : Fragment(R.layout.fragment_login) {

    /*private lateinit var binding: FragmentLoginBinding
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
        *//*binding = FragmentLoginBinding.inflate(layoutInflater)*//*
        onEnterClick()
    }

    private fun onEnterClick() {
        binding.loginEnterButton.setOnClickListener {

            val email = binding.loginUsername.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isValidEmail()) {
                loginViewModel.loginUser(
                    AuthRequest(
                        email = email,
                        password = password
                    )
                )
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    println("jalil login successful. ")
                }
                Status.LOADING -> {
                    println("jalil please wait... ")
                }
                Status.ERROR -> {
                    println("jalil something went wrong!!! ")
                }
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
    }*/
}