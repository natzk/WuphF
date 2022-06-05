package com.example.wuphf

import android.widget.ImageView

data class Spot(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val fav : Boolean,
//    val url: String
//    val image : ImageView
) {
    companion object {
        private var counter = 0L
    }
}