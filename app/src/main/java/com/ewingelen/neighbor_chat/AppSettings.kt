package com.ewingelen.neighbor_chat

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val userId: String? = null,
    val userName: String? = null
)