package com.example.prototype.features.users.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.prototype.databinding.FragmentUsersBinding
import com.example.prototype.features.users.data.Ad
import com.example.prototype.features.users.data.User
import com.example.prototype.userRow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var userViewModel: UsersViewModel

    private val users = mutableListOf<User>()
    private val ads = mutableListOf<Ad>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        binding = FragmentUsersBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFakeDataSet()

        showUserRecycler(users, ads)

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

    private fun initFakeDataSet() {
        repeat(10) {
            users.add(
                User(
                    "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg",
                    "hosseinmirzaei@gmail.com",
                    "Hossein",
                    it,
                    "Mirzaei"
                )
            )
            ads.add(
                Ad("Partsoftware", "We develop Android applications", "")
            )
        }
    }

}