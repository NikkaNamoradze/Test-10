package com.example.test10.domain.model

data class ItemModel(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val isTyping: Boolean,
    val lastMessage: String?,
    val lastName: String,
    val messageType: String,
    val unreaMessage: Int,
    val updatedDate: Long?
)

