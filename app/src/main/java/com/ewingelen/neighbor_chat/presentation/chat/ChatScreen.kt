package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.chat.components.MessagesSection
import com.ewingelen.neighbor_chat.presentation.chat.components.SendMessageSection
import com.ewingelen.neighbor_chat.presentation.chat.components.TopBar
import com.ewingelen.neighbor_chat.presentation.ui.theme.LightBlue
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceNormal
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall
import com.ewingelen.neighbor_chat.presentation.util.Screen
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages = viewModel.messages
    val message = viewModel.message.value
    val user = viewModel.user.value

    val listScrollState = rememberLazyListState(Int.MAX_VALUE)
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val alarmSoundUri = RawResourceDataSource.buildRawResourceUri(R.raw.alarm)
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(alarmSoundUri))
            prepare()
        }
    }

    var previousIndex by remember { mutableStateOf(listScrollState.firstVisibleItemIndex) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
    ) {
        TopBar(
            onSignOutClick = {
                navController.navigate(Screen.LoginScreen.route)
                viewModel.signOut()
            },
            onSlideDownClick = {
                coroutineScope.launch {
                    listScrollState.scrollToItem(messages.size)
                }
            }
        )

        MessagesSection(
            messages = messages,
            listState = listScrollState,
            userId = user.id,
            soundSwitch = { exoPlayer.playWhenReady = !exoPlayer.playWhenReady },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = SpaceSmall)
        )

        Spacer(modifier = Modifier.height(SpaceNormal))

        SendMessageSection(
            message = message,
            setMessage = { viewModel.setMessage(it) },
            sendMessage = {
                if (message.isNotBlank()) {
                    if (viewModel.isMessageAlert()) exoPlayer.playWhenReady = true
                    viewModel.sendMessage()
                    coroutineScope.launch {
                        listScrollState.scrollToItem(messages.size)
                    }
                }
            },
            onOpenKeyboard = {
                coroutineScope.launch {
                    previousIndex = listScrollState.firstVisibleItemIndex
                    listScrollState.scrollToItem(
                        listScrollState.firstVisibleItemIndex +
                                listScrollState.layoutInfo.visibleItemsInfo.lastIndex
                    )
                }
            },
            onCloseKeyboard = {
                coroutineScope.launch {
                    listScrollState.scrollToItem(previousIndex)
                }
            },
            modifier = Modifier.padding(horizontal = SpaceNormal)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))
    }
}