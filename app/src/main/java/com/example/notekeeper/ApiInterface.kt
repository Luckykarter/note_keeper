package com.example.notekeeper

import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("user/login/")
    fun login(@Body loginData: LoginInfo): Call<TokenInfo>

    @GET("garage/customers/")
    fun getCustomers(@Header("Authorization") token: String) : Call<PageResult>

    companion object {

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
