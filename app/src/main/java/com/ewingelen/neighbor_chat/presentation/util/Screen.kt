package com.ewingelen.neighbor_chat.presentation.util

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object ChatScreen: Screen("chat_screen")

    fun withArgs(vararg args: String) =
        buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
}
