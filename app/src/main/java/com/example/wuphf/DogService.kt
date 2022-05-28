package com.example.wuphf

import retrofit2.Response
import retrofit2.http.GET

interface DogService {

    @GET(Constants.BASE_URL)
    suspend fun getAllDogs() : Response<AllDogs>

    @GET(Constants.BASE_URL)
    suspend fun getDog(message : String) : Response<Dog>
}