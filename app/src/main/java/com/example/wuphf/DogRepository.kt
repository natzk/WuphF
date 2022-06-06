package com.example.wuphf
//
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class DogRepository @Inject constructor(
//    private val remoteDataSource: DogRemoteDataSource,
//    private val localDataSource : DogDao
//
//
//){
//    fun getDogs() = performFetchingAndSaving(
//        {localDataSource.getAllDogs()},
//        {remoteDataSource.getDogs()},
//        {localDataSource.insertDogs()}
//    )
//
//    fun getDog(id : String) = performFetchingAndSaving(
//        {localDataSource.getDog(id)},
//        {remoteDataSource.getDog(id)},
//        {localDataSource.insertDog(it)}
//    )
//}