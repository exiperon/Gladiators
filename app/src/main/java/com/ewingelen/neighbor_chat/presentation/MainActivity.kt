package com.ewingelen.neighbor_chat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ewingelen.neighbor_chat.presentation.components.LoadingScreen
import com.ewingelen.neighbor_chat.presentation.util.Navigation
import com.ewingelen.neighbor_chat.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {
            NeighborChatApp {
                if (viewModel.loadingFinished.value) {
                    val startDestination =
                        if (viewModel.userIsAuthorized.value) Screen.ChatScreen.route
                        else Screen.LoginScreen.route
                    Navigation(startDestination)
                } else {
                    LoadingScreen()
                }
            }
        }
    }
}

