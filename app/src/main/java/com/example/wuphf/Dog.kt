package com.example.wuphf

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
    //val id: Int,
    @PrimaryKey
    val message: String
){
    //body of the class
}