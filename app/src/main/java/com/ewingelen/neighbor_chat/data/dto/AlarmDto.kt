package com.ewingelen.neighbor_chat.data.dto

data class AlarmDto(
    val alerts: List<AlertDto>,
    val disclaimer: String,
    val meta: MetaDto
)