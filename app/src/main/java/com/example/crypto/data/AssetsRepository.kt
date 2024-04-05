package com.example.crypto.data

import com.example.crypto.model.AssetsResponse
import com.example.crypto.service.RetrofitInstance

class AssetsRepository {
    private val assetsService = RetrofitInstance.assetsService

    suspend fun getAssets(): AssetsResponse {
        return assetsService.getAssets()
    }
}