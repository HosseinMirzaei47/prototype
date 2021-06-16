package com.example.prototype.features.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.prototype.R
import com.example.prototype.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val userViewModel: UsersViewModel by viewModels()

    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUsersBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        println("jalil oncreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRetryClick()
        usersRecyclerSetup()
        println("jalil onCteate")
        fetchUsers()
    }

    override fun onDestroyView() {
        println("jalil onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        println("jalil onDestroy")
        super.onDestroy()
    }

    override fun onStop() {
        println("jalil onStop")
        super.onStop()
    }

    override fun onResume() {
        println("jalil onResume")
        super.onResume()
    }

    private fun fetchUsers() {
        lifecycleScope.launchWhenCreated {
            userViewModel.getUsers()?.collect {
                usersAdapter.submitData(it)
            }
        }
    }

    private fun usersRecyclerSetup() {
        usersAdapter =
            UsersAdapter { findNavController().navigate(R.id.action_usersFragment_to_fragmentTest) }
        displayLoadingState()
        binding.usersRecycler.apply {
            adapter = usersAdapter
        }
    }

    private fun displayLoadingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            usersAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressUsersFragment.isVisible = loadStates.refresh is LoadState.Loading
                binding.lottieUsersFragment.isVisible = loadStates.refresh is LoadState.Error
                binding.gifSubtitleUsersFragment.isVisible = loadStates.refresh is LoadState.Error
                binding.retryButtonUsersFragment.isVisible = loadStates.refresh is LoadState.Error
            }
        }
    }

    private fun onRetryClick() {
        binding.retryButtonUsersFragment.setOnClickListener { fetchUsers() }
    }

}