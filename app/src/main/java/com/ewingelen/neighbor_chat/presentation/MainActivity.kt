package com.ewingelen.neighbor_chat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ewingelen.neighbor_chat.presentation.util.Navigation
import com.ewingelen.neighbor_chat.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.userIsAuthorized.value
            }
        }

        val startDestination =
            if (viewModel.userIsAuthorized.value) Screen.ChatScreen.withArgs("ere")
            else Screen.LoginScreen.route

        setContent {
            NeighborChatApp {
                Navigation(startDestination)
            }
        }
    }
}

