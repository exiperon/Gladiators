package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ewingelen.neighbor_chat.domain.model.Message
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceNormal
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun MessagesSection(
    messages: List<Message>,
    listState: LazyListState,
    userId: String,
    soundSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(top = SpaceNormal),
        verticalArrangement = Arrangement.spacedBy(SpaceSmall),
        state = listState,
        modifier = modifier
    ) {
        items(items = messages) { message ->
            Column(modifier = modifier.fillMaxWidth()) {
                MessageItem(
                    userId = userId,
                    senderId = message.senderId,
                    senderName = message.senderName,
                    sendingTime = message.sendingTime,
                    messageText = message.text,
                    modifier = Modifier
                        .align(if (message.senderId == userId) Alignment.End else Alignment.Start)
                )

                if (message.isAlert) {
                    Spacer(modifier = Modifier.height(SpaceSmall))

                    AirRaidAlert(
                        soundIsPlaying = true,
                        soundSwitch = { soundSwitch() },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}