package com.example.flowersmvvmapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowersmvvmapplication.R
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {

    private var flowerListFragment: flowerListFragment?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.flowerListFragment = flowerListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.flowerListFragment, flowerListFragment!!)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        /*_subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);*/
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}