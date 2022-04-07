package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ewingelen.neighbor_chat.presentation.chat.components.MessagesSection
import com.ewingelen.neighbor_chat.presentation.chat.components.SendMessageSection
import com.ewingelen.neighbor_chat.presentation.ui.theme.LightBlue
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceNormal
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages = viewModel.messages
    val message = viewModel.message.value

//    val userName =
//    val userId =

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
            .padding(SpaceNormal)
    ) {
        MessagesSection(
            messages = messages,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        SendMessageSection(
            message = message,
            setMessage = { viewModel.setMessage(it) },
            sendMessage = { viewModel.sendMessage(senderId = "rere", senderName = "erer") }
        )

        Spacer(modifier = Modifier.height(SpaceSmall))

    }
}