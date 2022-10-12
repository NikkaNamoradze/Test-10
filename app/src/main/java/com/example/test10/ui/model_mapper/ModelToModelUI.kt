package com.example.test10.ui

import com.example.test10.domain.model.ItemModel
import com.example.test10.ui.model.ItemModelUI

fun ItemModel.toModelUI() = ItemModelUI(
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
