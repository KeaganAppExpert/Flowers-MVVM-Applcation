package com.example.flowersmvvmapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class flowerModel (
    @SerializedName("category")
    @Expose
    private var category: String? = null,

    @SerializedName("price")
    @Expose
    private var price: Double? = null,

    @SerializedName("instructions")
    @Expose
    private var instructions: String? = null,

    @SerializedName("photo")
    @Expose
    private var photo: String? = null,

    @SerializedName("name")
    @Expose
    private var name: String? = null,

    @SerializedName("productId")
    @Expose
    private var productId: Int? = null,
    ): Parcelable {

}