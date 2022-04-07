package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.use_cases.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: ChatUseCases
) : ViewModel() {

    private val _messages = mutableStateListOf<Message>()
    val messages: SnapshotStateList<Message> = _messages

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            getMessages()
//        }
    }

    fun setMessage(message: String) {
        _message.value = message
    }

    fun sendMessage(senderId: String, senderName: String) {
        val message = Message(
            text = message.value,
            isAlert = isMessageAlert(message.value),
            senderId = senderId,
            senderName = senderName
        )
        useCases.sendMessage(message)
    }

    fun getMessages() {

    }

    fun soundSwitch() {

    }

    private fun isMessageAlert(messageText: String) =
        messageText.lowercase().contains("тривога")
}