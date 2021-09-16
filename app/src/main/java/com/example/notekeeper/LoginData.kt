package com.example.notekeeper

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?
)

data class TokenInfo(
    @SerializedName("access") val access: String?,
    @SerializedName("refresh") val refresh: String?
)