package com.ewingelen.neighbor_chat.presentation.log_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.components.UnderlinedTextField
import com.ewingelen.neighbor_chat.presentation.ui.theme.Blue
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceLarge
import com.ewingelen.neighbor_chat.presentation.ui.theme.White
import com.ewingelen.neighbor_chat.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LogInViewModel = hiltViewModel(),
) {
    val userName = viewModel.userName.value
    val showError = viewModel.showError.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(color = Blue)
    ) {
        Text(
            text = stringResource(id = R.string.enter_your_name),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        UnderlinedTextField(
            hint = stringResource(id = R.string.name),
            value = userName,
            onValueChange = {
                viewModel.setUserName(it)
                viewModel.setShowError(false)
            },
            maxLines = 1,
            modifier = Modifier.width(128.dp)
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        Button(
            onClick = {
                if (userName.isNotEmpty()) {
                    navController.navigate(Screen.ChatScreen.withArgs(userName))
                    viewModel.logIn()
                } else {
                    viewModel.setShowError(true)
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = White)
        ) {
            Text(
                text = stringResource(id = R.string.log_in),
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(SpaceLarge))

        if (showError) {
            Text(
                text = stringResource(id = R.string.first_enter_a_name),
                color = MaterialTheme.colors.error
            )
        }
    }
}