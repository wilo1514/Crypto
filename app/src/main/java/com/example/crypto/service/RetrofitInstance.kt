package com.example.crypto.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL = "https://8384f84d-a291-4027-b408-5713b02712da.mock.pstmn.io/v2/"
    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val assetsService: AssetsService by lazy {
        retrofit.create(AssetsService::class.java)
    }
}