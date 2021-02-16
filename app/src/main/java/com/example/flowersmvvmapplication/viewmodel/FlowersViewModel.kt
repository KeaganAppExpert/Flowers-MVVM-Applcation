package com.example.flowersmvvmapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowersmvvmapplication.model.flowerModel
import com.example.flowersmvvmapplication.repository.FlowerRepository
import com.example.flowersmvvmapplication.util.Coroutines

class FlowersViewModel: ViewModel() {
    var flowersList = MutableLiveData<List<flowerModel>?>()

    init {
        refreshList()
    }

    private fun refreshList() {
        Coroutines.main {
            val flowerResponse = FlowerRepository().getFlowers()
            if(flowerResponse.isSuccessful){
                flowerResponse.body().let {
                    //this is where we add or change the mutable livedata value
                    flowersList?.value = it
                    Log.d("flowers_list", flowersList.toString())
                }
            }
        }
    }
}