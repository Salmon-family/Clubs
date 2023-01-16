package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@Composable
fun ClubHeaderDetails(
    state: ClubDetailsUiState,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.onBackground)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 24.dp)
                .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {

            Row {
                ClubCard(
                    imageVector = com.devfalah.ui.R.drawable.ic_menu_language,
                    text = state.detailsUiState.isClubPublic.toString()
                )

                ClubCard(
                    imageVector = com.devfalah.ui.R.drawable.ic_people,
                    text = state.membersCount.toString()
                )

                ClubCard(
                    imageVector = com.devfalah.ui.R.drawable.ic_comment,
                    text = state.postCount.toString()
                )

            }

        }
    }
}
