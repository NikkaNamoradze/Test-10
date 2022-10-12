package com.example.test10.data.response_handler

sealed class Resource<T : Any> {
    data class Success<T : Any>(val data: T?) : Resource<T>()
    data class Failure<T : Any>(val message: String?) : Resource<T>()
}