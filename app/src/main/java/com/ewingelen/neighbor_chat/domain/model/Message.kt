package com.ewingelen.neighbor_chat.domain.model

data class Message(
    val text: String,
    val isAlert: Boolean,
    val senderId: String,
    val senderName: String
)