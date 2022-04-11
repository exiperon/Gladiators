package com.ewingelen.neighbor_chat.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ewingelen.neighbor_chat.presentation.chat.ChatScreen
import com.ewingelen.neighbor_chat.presentation.log_in.LoginScreen

@Composable
fun Navigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.ChatScreen.route) {
            ChatScreen(navController)
        }
    }
}