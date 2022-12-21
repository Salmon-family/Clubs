package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun PrivateClubsBox(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_private_lock),
            contentDescription = "private"
        )

        HeightSpacer8()
        Text(
            text = "This club is private",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = PlusJakartaSans,
            color = LightPrimaryBrandColor
        )

        HeightSpacer8()
        Text(
            text = "Ask to join and wait for the acceptation",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            fontFamily = PlusJakartaSans,
            color = LightPrimaryBrandColor
        )

    }

}