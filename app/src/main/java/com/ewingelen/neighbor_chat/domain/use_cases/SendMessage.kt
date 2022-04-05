package com.ewingelen.neighbor_chat.domain.use_cases

import com.ewingelen.neighbor_chat.domain.repository.NeighborChatRepository
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val repository: NeighborChatRepository
) {
    operator fun invoke() {

    }
}