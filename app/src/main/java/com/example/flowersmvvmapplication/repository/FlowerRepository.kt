package com.example.flowersmvvmapplication.repository

import com.example.flowersmvvmapplication.api.ApiInterface
import com.example.flowersmvvmapplication.api.RetrofitClient
import com.example.flowersmvvmapplication.model.Flower_Model
import retrofit2.Response

class FlowerRepository {
    suspend fun getFlowers(): Response<List<Flower_Model>>{
        return RetrofitClient.getFlowerService(ApiInterface::class.java).getFlowerslist()
    }
}