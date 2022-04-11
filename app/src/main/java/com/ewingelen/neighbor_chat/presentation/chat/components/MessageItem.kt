package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.ewingelen.neighbor_chat.presentation.ui.theme.Cyan
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceExtraSmall
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall
import java.text.SimpleDateFormat

@Composable
fun MessageItem(
    userId: String,
    senderId: String,
    senderName: String,
    sendingTime: Long,
    messageText: String,
    modifier: Modifier = Modifier
) {
    val dateFormat =
        SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)

    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Cyan,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(SpaceSmall)) {
            if (userId != senderId) {
                Text(
                    text = senderName,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(SpaceExtraSmall))
            }

            Text(text = messageText)

            Spacer(modifier = Modifier.height(SpaceExtraSmall))

            Text(
                text = dateFormat.format(sendingTime),
                style = MaterialTheme.typography.body2
            )
        }
    }
}