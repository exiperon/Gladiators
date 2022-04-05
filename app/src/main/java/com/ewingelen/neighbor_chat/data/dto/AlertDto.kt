package com.ewingelen.neighbor_chat.data.dto

data class AlertDto(
    val calculated: Any,
    val finished_at: Any,
    val id: Int,
    val location_title: String,
    val location_type: String,
    val started_at: String,
    val updated_at: String
)