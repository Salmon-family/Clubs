package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.viewmodels.search.ClubUIState

@Composable
fun MyClubCard(
    myClub: ClubUIState,
    modifier: Modifier = Modifier,
    onClubClick: (ClubUIState) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClubClick(myClub) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,


        ) {

        CircleImage(
            painter = painterResource(id = R.drawable.club_icon),
            size = 64,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = myClub.title,
                style = AppTypography.subtitle2,
                color = LightPrimaryBlackColor
            )

            Text(
                text = myClub.description,
                style = AppTypography.caption,
                color = LightSecondaryBlackColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

        }

    }
}

@Preview
@Composable
private fun Preview() {
    MyClubCard(myClub = ClubUIState(
        0,
        "this club is for cooking this club is for cookingthis club is for cookingthis club is for cookingthis club is for cooking",
        "",
    ),
        onClubClick = {}
    )
}