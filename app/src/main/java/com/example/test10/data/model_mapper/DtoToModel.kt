package com.example.test10.data.model_mapper

import com.example.test10.data.remote.model.ItemDto
import com.example.test10.domain.model.ItemModel

fun ItemDto.toModel() = ItemModel(
    avatar = avatar,
    email = email,
    firstName = firstName,
    id = id,
    isTyping = isTyping,
    lastMessage = lastMessage,
    lastName = lastName,
    messageType = messageType,
    unreaMessage = unreaMessage,
    updatedDate = updatedDate
)