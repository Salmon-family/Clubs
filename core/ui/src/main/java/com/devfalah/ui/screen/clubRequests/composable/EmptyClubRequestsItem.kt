package com.devfalah.ui.screen.clubRequests.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.devfalah.ui.R
import com.devfalah.ui.composable.EmptyScreenIcon
import com.devfalah.ui.composable.EmptyScreenTexts
import com.devfalah.ui.composable.HeightSpacer8

@Composable
fun EmptyClubRequestsItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmptyScreenIcon(
            painter = painterResource(id = R.drawable.ic_club_request)
        )
        HeightSpacer8()
        EmptyScreenTexts(
            title = stringResource(R.string.nothing_to_show),
            description = stringResource(R.string.empty_club_requests_description)
        )
    }
}