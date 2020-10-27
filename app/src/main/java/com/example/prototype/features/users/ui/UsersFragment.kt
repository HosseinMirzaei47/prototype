package com.example.prototype.features.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.prototype.core.resource.Status
import com.example.prototype.databinding.FragmentUsersBinding
import com.example.prototype.features.users.data.Ad
import com.example.prototype.features.users.data.User
import com.example.prototype.userRow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val userViewModel: UsersViewModel by viewModels()

    private var users = mutableListOf<User>()
    private val ads = mutableListOf<Ad>()

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

        userViewModel.getAllUsers()
        userViewModel.allUsersResult.observe(viewLifecycleOwner, { resource ->
            if (resource.status == Status.SUCCESS) {
                resource.data?.let { updatedUserList ->
                    users = updatedUserList.toMutableList()
                    showUserRecycler(users, ads)
                }
                println("jalil $resource")
            } else if (resource.status == Status.ERROR) {
                println("jalil ERROR")
            }

            hideProgressbar()

        })

    }

    private fun showUserRecycler(users: List<User>, ads: List<Ad>) {
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
    }

    private fun hideProgressbar() {
        binding.progressUsers.visibility = View.INVISIBLE
    }

}