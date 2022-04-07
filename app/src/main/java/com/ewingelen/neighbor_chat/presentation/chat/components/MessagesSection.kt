package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun MessagesSection(
    messages: List<Message>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(SpaceSmall),
        modifier = modifier
    ) {
        items(items = messages) { message ->
            Column(
//                horizontalAlignment = if (true) Alignment.End else Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
//                        start = if (message.isSentByMe) SpaceNormal else 0.dp,
//                        end = if (message.isSentByMe) 0.dp else SpaceNormal
                    )

            ) {
                MessageItem(
                    senderName = message.senderName,
                    messageText = message.text,
                    modifier = Modifier.padding(SpaceSmall)
                )

                if (message.isAlert) {
                    Spacer(modifier = Modifier.height(SpaceSmall))

                    AirRaidAlert(
                        soundIsPlaying = true,
                        soundSwitch = {}
                    )
                }
            }
        }
    }
}