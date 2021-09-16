package com.example.notekeeper

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("passport_number") val passportNumber: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("age") val age: Int,
    @SerializedName("city") val city: String
)