package com.example.flowersmvvmapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val basePhotoURL = "https://services.hanselandpetal.com/photos/"
    Glide.with(imageView.context).load(basePhotoURL + url).into(imageView)
}