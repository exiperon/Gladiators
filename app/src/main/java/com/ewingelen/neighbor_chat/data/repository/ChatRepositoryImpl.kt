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

    override fun getUser(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
//            val user = dataStore.data.map {
//                User(it.userId!!, it.userName!!)
//            }
            emit(Resource.Success(User("rere", "erer")))
        } catch (e: Exception) {
            emit(Resource.Error<User>("An unexpected error occurred"))
        }
    }

    override fun sendMessage(message: Message) {
        try {
            messagesCollectionRef.add(message)
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

//    override fun getMessages(): List<Message> {
//        try {
//            messagesCollectionRef.get().addOnSuccessListener {
//
//            }
//        } catch (e: Exception) {
//            return emptyList()
//        }
//    }
}