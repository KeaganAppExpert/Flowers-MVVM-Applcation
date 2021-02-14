package com.example.flowersmvvmapplication.api

import com.example.flowersmvvmapplication.model.flowerModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("feeds/flowers.json")
    fun getFlowerslist(): Observable<List<flowerModel>>

    @GET("images/{imageURL}")
    fun getImageUrl(@Path("imageURL") imageURL: String): Observable<List<flowerModel>>
}