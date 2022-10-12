package com.example.test10.data.response_handler

import retrofit2.Response

interface ResponseHandler {
    suspend fun <T : Any> handleResponse(request: suspend () -> Response<T>): Resource<T> {
        return try {
            val result = request.invoke()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return Resource.Success(body)
            } else {
                Resource.Failure(result.message())
            }
        } catch (e: Throwable) {
            Resource.Failure("something went wrong")
        }
    }
}