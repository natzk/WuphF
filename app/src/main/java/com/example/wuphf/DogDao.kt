package com.example.wuphf

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface DogDao {

    @Query("SELECT * FROM dogs")
    fun getAllDogs() : LiveData<List<Dog>>

    @Query("SELECT * FROM dogs WHERE message = :message")
    fun getDog(message : String) : LiveData<Dog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog : Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogs(dog : List<Dog>)

}