package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBrandColor
import com.devfalah.viewmodels.myClubs.SpecialClubsUIState

@Composable
fun SpecialClubItem(
    state: SpecialClubsUIState,
    iconId: Int,
    clubTitle: String,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {

    Column(
        modifier = modifier.clickable { onClick(state.id) }
            .clip(CircleShape)
            .background(LightSecondaryBrandColor)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround

    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "icon",
            tint = LightPrimaryBrandColor
        )

        Text(text = clubTitle)
    }
}

@Preview
@Composable
private fun Preview() {
//    SpecialClubItem(R.drawable.art_icon, "art")
}