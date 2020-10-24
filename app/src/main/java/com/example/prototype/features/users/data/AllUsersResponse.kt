package com.example.prototype.features.users.data

import com.google.gson.annotations.SerializedName

data class AllUsersResponse(
    val ad: Ad,
    @SerializedName("data")
    val `users`: List<User>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)