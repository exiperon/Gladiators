package com.ewingelen.neighbor_chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.chat.components.MessagesSection
import com.ewingelen.neighbor_chat.presentation.chat.components.SendMessageSection
import com.ewingelen.neighbor_chat.presentation.ui.theme.*
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

    val listScrollState = rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE)
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val alarmSoundUri = RawResourceDataSource.buildRawResourceUri(R.raw.alarm)
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(alarmSoundUri))
            prepare()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBlue)
    ) {
        TopAppBar(
            actions = {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                        viewModel.signOut()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = stringResource(id = R.string.exit),
                        tint = White
                    )
                }
            },
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            backgroundColor = Blue
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
                if (message.isNotEmpty()) {
                    if (viewModel.isMessageAlert()) exoPlayer.playWhenReady = true
                    viewModel.sendMessage()
                    coroutineScope.launch {
                        listScrollState.animateScrollToItem(messages.size)
                    }
                }
            },
            modifier = Modifier.padding(horizontal = SpaceNormal)
        )

        Spacer(modifier = Modifier.height(SpaceSmall))
    }
}