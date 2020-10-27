package com.example.prototype.features.home.data

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String
)