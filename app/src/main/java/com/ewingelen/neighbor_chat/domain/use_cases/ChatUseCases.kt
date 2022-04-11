package com.ewingelen.neighbor_chat.domain.use_cases

data class ChatUseCases(
    val logIn: LogIn,
    val signOut: SignOut,
    val getUser: GetUser,
    val sendMessage: SendMessage,
    val getMessages: GetMessages
)