package com.ewingelen.neighbor_chat

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val userIsAuthorized: Boolean = false,
    val userName: String? = null
)