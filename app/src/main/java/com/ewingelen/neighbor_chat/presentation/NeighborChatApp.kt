package com.ewingelen.neighbor_chat.presentation

import androidx.compose.runtime.Composable
import com.ewingelen.neighbor_chat.presentation.ui.theme.NeighborChatTheme

@Composable
fun NeighborChatApp(content: @Composable () -> Unit) {
    NeighborChatTheme {
        content()
    }
}