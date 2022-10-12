package com.example.test10.domain.use_case

import com.example.test10.domain.repository.Repository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getItems()
}