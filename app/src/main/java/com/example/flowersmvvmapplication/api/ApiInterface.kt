package com.example.flowersmvvmapplication.api

import com.example.flowersmvvmapplication.model.Flower_Model
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("feeds/flowers.json")
    suspend fun getFlowerslist(): Response<List<Flower_Model>>
}