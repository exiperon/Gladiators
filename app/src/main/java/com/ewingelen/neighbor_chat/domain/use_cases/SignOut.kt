package com.ewingelen.neighbor_chat.domain.use_cases

import com.ewingelen.neighbor_chat.domain.repository.ChatRepository
import javax.inject.Inject

class SignOut @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke() {
        repository.signOut()
    }
}