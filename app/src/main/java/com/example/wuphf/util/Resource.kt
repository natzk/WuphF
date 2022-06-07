package com.example.wuphf.util

sealed class Resource<out T> constructor(val data: T? = null,
                                             val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}