package com.thechance.ui.screens.chat.composable

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thechance.ui.R


@Composable
fun EmptyChatScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmptyScreenIcon(
            painter = painterResource(id = R.drawable.ic_chat)
        )

        Spacer(modifier = Modifier.height(8.dp))

        EmptyScreenTexts(
            title = stringResource(R.string.nothing_to_show),
            description = stringResource(R.string.empty_chat_description)
        )
    }
}