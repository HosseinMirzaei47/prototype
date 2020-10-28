package com.example.prototype.features.users.data

import com.squareup.moshi.Json

data class AllUsersResponse(
    val ad: Ad,
    @field:Json(name = "data")
    val users: List<User>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)