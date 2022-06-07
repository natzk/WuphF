package com.example.wuphf.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
    //val id: Int,
    @PrimaryKey
    val message: String,

    // val name: String,
    // val number: String,

    //   val isFavorite : Int = 0  // 1
)