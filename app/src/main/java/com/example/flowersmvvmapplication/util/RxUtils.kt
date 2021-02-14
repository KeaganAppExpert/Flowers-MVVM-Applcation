package com.example.flowersmvvmapplication.util

import rx.Subscription
import rx.subscriptions.CompositeSubscription

class RxUtils {
    companion object {
        fun unsubscribeIfNotNull(subscription: Subscription?) {
            subscription?.unsubscribe()
        }

        fun getNewCompositeSubIfUnsubscribed(subscription: CompositeSubscription?): CompositeSubscription? {
            return if (subscription == null || subscription.isUnsubscribed) {
                CompositeSubscription()
            } else subscription
        }
    }
}