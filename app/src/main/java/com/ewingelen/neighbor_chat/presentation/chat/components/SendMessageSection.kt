package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.components.UnderlinedTextField
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall
import com.ewingelen.neighbor_chat.presentation.ui.theme.White

@Composable
fun SendMessageSection(
    message: String,
    setMessage: (String) -> Unit,
    sendMessage: () -> Unit,
    onOpenKeyboard: () -> Unit,
    onCloseKeyboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        UnderlinedTextField(
            hint = stringResource(id = R.string.enter_a_message),
            value = message,
            onValueChange = { setMessage(it) },
            maxLines = 3,
            onOpenKeyboard = { onOpenKeyboard() },
            onCloseKeyboard = { onCloseKeyboard() },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        IconButton(
            onClick = { sendMessage() }
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = stringResource(id = R.string.send_message),
                tint = White
            )
        }
    }
}