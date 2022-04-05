package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ewingelen.neighbor_chat.presentation.ui.theme.Cyan
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun MessageItem(
    senderName: String?,
    messageText: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Cyan
    ) {
        Column(modifier = modifier) {
            if (senderName != null) {
                Text(text = senderName)

                Spacer(modifier = Modifier.height(SpaceSmall))
            }

            Text(
                text = messageText,
                style = MaterialTheme.typography.body2
            )
        }
    }
}