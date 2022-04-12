package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.ui.theme.Blue
import com.ewingelen.neighbor_chat.presentation.ui.theme.White

@Composable
fun TopBar(
    onSignOutClick: () -> Unit,
    onSlideDownClick: () -> Unit
) {
    TopAppBar(
        actions = {
            IconButton(
                onClick = { onSignOutClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = stringResource(id = R.string.exit),
                    tint = White
                )
            }
            IconButton(
                onClick = { onSlideDownClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.side_down),
                    tint = White
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = Blue
    )
}