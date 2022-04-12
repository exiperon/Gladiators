package com.ewingelen.neighbor_chat.data.repository

import androidx.datastore.core.DataStore
import com.ewingelen.neighbor_chat.AppSettings
import com.ewingelen.neighbor_chat.core.Resource
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.model.User
import com.ewingelen.neighbor_chat.domain.repository.ChatRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject




class ChatRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<AppSettings>
) : ChatRepository {

    private val messagesCollectionRef = Firebase.firestore.collection("messages")

    override suspend fun logIn(userName: String) {
        dataStore.updateData {
            it.copy(
                userId = UUID.randomUUID().toString(),
                userName = userName,
            )
        }
    }

    override suspend fun signOut() {
        dataStore.updateData {
            it.copy(
                userId = null,
                userName = null
            )
        }
    }

    override fun getUser(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            dataStore.data.collect {
                if (it.userId != null && it.userName != null) {
                    emit(Resource.Success(User(it.userId, it.userName)))
                } else {
                    emit(Resource.Error<User>("User is unauthorized"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error<User>("An unexpected error occurred"))
        }
    }

    override fun sendMessage(message: Message) {
        try {
            messagesCollectionRef.add(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getMessages(): Flow<Message> = flow {
        val documents = messagesCollectionRef.orderBy("sendingTime").get().await()

        for (document in documents) {
            emit(Message(
                text = document.get("text") as String,
                isAlert = document.get("alert") as Boolean,
                senderName = document.get("senderName") as String,
                senderId = document.get("senderId") as String,
                sendingTime = document.get("sendingTime") as Long
            ))
        }
    }
}