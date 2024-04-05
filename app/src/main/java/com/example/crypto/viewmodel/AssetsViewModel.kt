package com.example.crypto.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.data.AssetsRepository
import com.example.crypto.model.Asset
import kotlinx.coroutines.launch

class AssetsViewModel: ViewModel() {

    private val assetsRepository = AssetsRepository()

    var assets = mutableStateListOf<Asset>()

    fun fetchAssets(){

        viewModelScope.launch {
            val response = assetsRepository.getAssets().data
            val assetsResponse = response.map { assetResponse ->
                val price = assetResponse.priceUsd.format("%.2f").toDouble()
                val percentage = assetResponse.changePercent24Hr?.format("%.2f")?.toDouble() ?: 0.0
                Asset(
                    assetResponse.id,
                    assetResponse.name,
                    assetResponse.symbol,
                    price,
                    percentage
                )
            }
            assets.addAll(assetsResponse)
        }
    }
}