package com.example.test10.data.remote.api_service

import com.example.test10.data.remote.model.ItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf")
    suspend fun getItems(): Response<List<ItemDto>>
}