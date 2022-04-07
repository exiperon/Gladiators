package com.ewingelen.neighbor_chat.domain.use_cases

import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(message: Message) {
        repository.sendMessage(message)
    }
}