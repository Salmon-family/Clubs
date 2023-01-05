package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@Composable
fun ClubStats(
    state: ClubDetailsUiState,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        Row {
            ClubCard(
                imageVector = R.drawable.ic_menu_language,
                text = state.detailsUiState.isClubPublic.toString()
            )

            ClubCard(
                imageVector = R.drawable.ic_people,
                text = state.membersCount.toString()
            )

            ClubCard(
                imageVector = R.drawable.ic_comment,
                text = state.postCount.toString()
            )

        }

    }
}