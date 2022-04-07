package com.ewingelen.neighbor_chat.domain.repository

import com.ewingelen.neighbor_chat.core.Resource
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun logIn(userName: String)

    fun getUser(): Flow<Resource<User>>

    fun sendMessage(message: Message)

//    fun getMessages(): Flow<List<Message>>
}