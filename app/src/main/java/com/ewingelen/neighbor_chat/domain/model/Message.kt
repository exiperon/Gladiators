package com.ewingelen.neighbor_chat.domain.model

data class Message(
    val isSentByMe: Boolean,
    val sendingTime: Int,
    val text: String,
    val isAlert: Boolean,
    val senderName: String? = null,
)