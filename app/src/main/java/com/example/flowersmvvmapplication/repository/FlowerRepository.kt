package com.example.flowersmvvmapplication.repository

import com.example.flowersmvvmapplication.api.ApiInterface
import com.example.flowersmvvmapplication.api.RetrofitClient
import com.example.flowersmvvmapplication.model.flowerModel
import retrofit2.Response

class FlowerRepository {
    suspend fun getFlowers(): Response<List<flowerModel>>{
        return RetrofitClient.getFlowerService(ApiInterface::class.java).getFlowerslist()
    }
}