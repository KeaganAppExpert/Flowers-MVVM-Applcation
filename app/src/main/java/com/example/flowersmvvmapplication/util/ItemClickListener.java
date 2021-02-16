package com.example.flowersmvvmapplication.util;

import android.view.View;

public interface ItemClickListener {
    /**
     *
     * @param view
     * @param position
     * @param isLongClick
     */
    void onClick(View view, int position, boolean isLongClick);
    void OnError(View view, String message);
}
