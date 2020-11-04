package com.example.prototype.features.auth.data

import com.squareup.moshi.Json


data class AuthRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)