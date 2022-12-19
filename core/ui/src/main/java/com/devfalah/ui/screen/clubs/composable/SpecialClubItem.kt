package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun SpecialClubItem(
    iconId: Int,
    clubTitle: String
) {

    Column(
        modifier = Modifier
            .size(98.dp)
            .clip(RoundedCornerShape(100))
            .background(LightSecondaryBrandColor)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

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
    SpecialClubItem(R.drawable.art_icon, "art")
}