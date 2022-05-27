package com.example.wuphf

import android.widget.ImageView

class ItemModel {
//    var image = 0
    lateinit var name : String
    lateinit var city : String
    lateinit var image : ImageView

    constructor() {}
    constructor(image: ImageView, name: String, city : String) {
        this.image = image
        this.name = name
        this.city = city
    }

    @JvmName("getName1")
    fun getName() : String {
        return name
    }

    @JvmName("getCity1")
    fun getCity() : String{
        return city
    }

    @JvmName("getImage1")
    fun getImage() : ImageView {
        return image
    }
}