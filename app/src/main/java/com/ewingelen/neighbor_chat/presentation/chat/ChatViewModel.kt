package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.neighbor_chat.core.Resource
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.model.User
import com.ewingelen.neighbor_chat.domain.use_cases.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCases: ChatUseCases
) : ViewModel() {

    private val _messages = mutableStateListOf<Message>()
    val messages: SnapshotStateList<Message> = _messages

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    private val _user = mutableStateOf(User("", ""))
    val user: State<User> = _user

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUser()
            getMessages()
        }
    }

    fun setMessage(message: String) {
        _message.value = message
    }

    fun sendMessage() {
            val message = Message(
                text = message.value,
                isAlert = isMessageAlert(),
                senderId = _user.value.id,
                senderName = _user.value.name,
                sendingTime = System.currentTimeMillis()
            )
            useCases.sendMessage(message)
            _messages.add(message)
            _message.value = ""
    }

    private fun getUser() {
        useCases.getUser().onEach { result ->
            if (result is Resource.Success && result.data != null) {
                _user.value = User(result.data.id, result.data.name)
            }
        }.launchIn(viewModelScope)
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.signOut()
        }
    }

    private suspend fun getMessages() {
        _messages.addAll(useCases.getMessages())
    }

    fun isMessageAlert() =
        message.value.lowercase().contains("тривога") ||
                message.value.lowercase().contains("повітряна") ||
                message.value.lowercase().contains("ракетн")
}