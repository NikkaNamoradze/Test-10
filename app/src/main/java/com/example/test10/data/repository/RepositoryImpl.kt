package com.example.test10.data.repository

import com.example.test10.data.response_handler.Resource
import com.example.test10.data.response_handler.ResponseHandler
import com.example.test10.data.model_mapper.toModel
import com.example.test10.data.remote.api_service.ApiService
import com.example.test10.domain.model.ItemModel
import com.example.test10.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository,
    ResponseHandler {

    override suspend fun getItems(): Flow<Resource<List<ItemModel>>> = flow {
        when (val result = handleResponse { apiService.getItems() }) {
            is Resource.Success -> emit(Resource.Success(result.data?.map { it.toModel() }))
            is Resource.Failure -> emit(Resource.Failure(result.message))
        }
    }
}