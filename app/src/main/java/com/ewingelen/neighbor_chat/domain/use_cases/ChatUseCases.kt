package com.ewingelen.neighbor_chat.domain.use_cases

data class ChatUseCases(
    val logIn: LogIn,
    val getUser: GetUser,
    val sendMessage: SendMessage
)