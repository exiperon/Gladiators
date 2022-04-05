package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.use_cases.NeighborChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: NeighborChatUseCases
) : ViewModel() {

    private val _messages = mutableStateListOf<Message>(
        Message(
            isSentByMe = true,
            sendingTime = 123,
            text = "Привіт",
            isAlert = false
        ),
        Message(
            isSentByMe = false,
            sendingTime = 123,
            text = "Чия це нова машина під домом стоїть?",
            isAlert = false,
            senderName = "Жанна",
        ),
        Message(isSentByMe = true, sendingTime = 123, text = "Як справи, сусіди?", false),
        Message(isSentByMe = false, sendingTime = 123, text = "...", false, "Анна"),
        Message(
            isSentByMe = false,
            sendingTime = 123,
            text = "Повітряна тривога!!",
            true,
            "Артем",
        ),
    )
    val messages: SnapshotStateList<Message> = _messages

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            getMessages()
//        }
    }

    private fun getMessages() {

    }

    fun setMessage(message: String) {
        _message.value = message
    }

    fun sendMessage() {
        useCases.sendMessage()
    }

    fun soundSwitch() {

    }
}