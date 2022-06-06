package com.example.wuphf

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllDogsViewModel @Inject constructor(private val dogRemoteDataSource: DogRemoteDataSource) : ViewModel() {

    suspend fun getAllDogsRDS() = dogRemoteDataSource.getDogs()

}