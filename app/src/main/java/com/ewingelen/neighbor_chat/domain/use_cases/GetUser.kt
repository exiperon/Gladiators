package com.ewingelen.neighbor_chat.domain.use_cases

import com.ewingelen.neighbor_chat.domain.repository.ChatRepository
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke() = repository.getUser()
}