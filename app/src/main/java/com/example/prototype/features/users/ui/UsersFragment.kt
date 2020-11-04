package com.example.prototype.features.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.prototype.databinding.FragmentUsersBinding
import com.example.prototype.features.users.data.Ad
import com.example.prototype.features.users.data.User
import com.example.prototype.userRow
import dagger.hilt.android.AndroidEntryPoint

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter = UsersAdapter()
        binding.usersRecycler.apply {
            adapter = usersAdapter
        }

        lifecycleScope.launchWhenCreated {
            userViewModel.getUsers()?.observe(viewLifecycleOwner) {
                usersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                hideNothingFoundViews()
            }
        }

    }

    private fun hideNothingFoundViews() {
        binding.progressUsers.visibility = View.GONE
        binding.gifSubtitleUsersFragment.visibility = View.GONE
        binding.lottieUserFragment.visibility = View.GONE
    }

    /* Epoxy recyclerView implementation(doesn't use Paging 3) */
    private fun showUserRecycler(users: List<User>, ads: List<Ad>) {
        binding.usersRecycler.adapter = UsersAdapter()

        if (users.isNotEmpty()) {
            binding.usersRecycler.withModels {
                users.forEachIndexed { index, user ->
                    userRow {
                        id(index)
                        user(user)
                        ads.forEachIndexed { _, ad ->
                            ad(ad)
                        }
                    }
                }
            }

            hideNothingFoundViews()
        }
    }

}