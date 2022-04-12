package com.ewingelen.neighbor_chat.presentation.components

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.ewingelen.neighbor_chat.presentation.ui.theme.Black
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun UnderlinedTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onOpenKeyboard: () -> Unit = {},
    onCloseKeyboard: () -> Unit = {},
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    val isKeyboardOpen by keyboardAsState()

    if (isKeyboardOpen == Keyboard.Opened) {
        onOpenKeyboard()
    } else {
        onCloseKeyboard()
    }

    Column(modifier = modifier) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(horizontal = SpaceSmall)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.caption,
                singleLine = singleLine,
                maxLines = maxLines,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth()
            )

            if (value.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.caption
                )
            }
        }

        Spacer(modifier = Modifier.height(SpaceSmall))

        Divider(color = Black)
    }
}

enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}