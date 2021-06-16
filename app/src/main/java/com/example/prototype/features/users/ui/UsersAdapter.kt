package com.example.prototype.features.users.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.prototype.databinding.EpoxyHolderUserRowBinding
import com.example.prototype.features.users.data.User
import com.example.prototype.features.users.data.UsersViewHolder

class UsersAdapter(
    private val action: () -> Unit
) : PagingDataAdapter<User, UsersViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = EpoxyHolderUserRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.binding.iconPerson.setOnClickListener { action() }
        holder.binding.user = getItem(position)
    }

    companion object {
        val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.first_name == newItem.first_name

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }

    }
}
