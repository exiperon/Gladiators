package com.ewingelen.neighbor_chat.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ewingelen.neighbor_chat.R
import com.ewingelen.neighbor_chat.presentation.ui.theme.Red
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceExtraSmall
import com.ewingelen.neighbor_chat.presentation.ui.theme.SpaceSmall

@Composable
fun AirRaidAlert(
    soundIsPlaying: Boolean,
    soundSwitch: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Red, shape = MaterialTheme.shapes.large)
            .padding(vertical = SpaceExtraSmall, horizontal = SpaceSmall)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        Text(
            text = stringResource(id = R.string.air_raid_alert),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(SpaceSmall))

        Icon(
            painterResource(
                id = if (soundIsPlaying) R.drawable.ic_volume_on
                else R.drawable.ic_volume_off
            ),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape).clickable { soundSwitch() }
        )
    }
}