package com.example.wuphf

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dogDao() : DogDao

    companion object{

        @Volatile
        private var instance : AppDataBase? = null

        fun getDataBase(context : Context) : AppDataBase =
            instance ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "dogs")
                    .fallbackToDestructiveMigration().build().also{
                        instance = it
                    }
            }
    }
}