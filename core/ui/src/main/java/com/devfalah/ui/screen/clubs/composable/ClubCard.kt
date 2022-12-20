package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.viewmodels.myClubs.ClubsState

@Composable
fun ClubCard(
    club: ClubsState,
    onClubClick: (ClubsState) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAFAFA))
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClubClick(club) }
    ) {

        Text(
            text = club.title,
            style = AppTypography.subtitle2,
            color = LightSecondaryBlackColor
        )

        HeightSpacer8()

        Text(
            text = club.description,
            style = AppTypography.caption,
            color = LightSecondaryBlackColor
        )

    }
}

@Preview
@Composable
private fun Preview() {
    ClubCard(
        ClubsState(
            title = "cooking club",
            description = "club for cooking lovers",
            privacy = "1",
            id = 0,
        )
    ) {}
}