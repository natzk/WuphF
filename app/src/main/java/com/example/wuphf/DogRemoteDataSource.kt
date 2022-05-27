package com.example.wuphf

import javax.inject.Inject

class DogRemoteDataSource @Inject constructor(private val dogService: DogService) : BaseDataSource() {

    suspend fun getDogs() = getResult { dogService.getAllDogs() }
    suspend fun getDog(message : String) = getResult { dogService.getDog(message) }
}