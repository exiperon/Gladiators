package com.ewingelen.neighbor_chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val photo: String
)