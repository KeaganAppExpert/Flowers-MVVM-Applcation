package com.example.flowersmvvmapplication.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class flowerModel {
    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("price")
    @Expose
    private var price: Double? = null

    @SerializedName("instructions")
    @Expose
    private var instructions: String? = null

    @SerializedName("photo")
    @Expose
    private var photo: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("productId")
    @Expose
    private var productId: Int? = null

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getPrice(): Double? {
        return price
    }

    fun setPrice(price: Double?) {
        this.price = price
    }

    fun getInstructions(): String? {
        return instructions
    }

    fun setInstructions(instructions: String?) {
        this.instructions = instructions
    }

    fun getPhoto(): String? {
        return photo
    }

    fun setPhoto(photo: String?) {
        this.photo = photo
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getProductId(): Int? {
        return productId
    }

    fun setProductId(productId: Int?) {
        this.productId = productId
    }
}