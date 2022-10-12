package com.example.test10.domain.repository

import com.example.test10.data.response_handler.Resource
import com.example.test10.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getItems(): Flow<Resource<List<ItemModel>>>
}