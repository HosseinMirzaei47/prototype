package com.example.prototype.features.users.data

data class AllUsersResponse(
    val ad: Ad,
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)