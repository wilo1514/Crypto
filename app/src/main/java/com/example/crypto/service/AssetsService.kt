package com.example.crypto.service


import com.example.crypto.model.AssetsResponse
import retrofit2.http.GET

interface AssetsService {
    @GET("assets")
    suspend fun getAssets():AssetsResponse
}