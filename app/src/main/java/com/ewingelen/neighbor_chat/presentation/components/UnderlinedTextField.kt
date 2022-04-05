package com.ewingelen.neighbor_chat.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ewingelen.neighbor_chat.presentation.ui.theme.Black
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun UnderlinedTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(horizontal = SpaceSmall)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.caption,
                maxLines = maxLines,
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