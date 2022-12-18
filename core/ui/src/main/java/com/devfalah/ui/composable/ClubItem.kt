package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.search.ClubUIState

@Composable
fun ClubItem(
    modifier: Modifier = Modifier,
    state: ClubUIState,
    onClubSelected: (Int) -> Unit
) {

    Row(
        modifier = modifier
            .nonRippleEffect { onClubSelected(state.id) }
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LightCardBackgroundColor)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painter = painterResource(R.drawable.ic_clubs_filled),
            contentDescription = "profile user",
            modifier = modifier
                .clip(shape = CircleShape)
                .background(LightSecondaryBrandColor)
                .padding(12.dp)
                .size(56.dp),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = LightPrimaryBrandColor)
        )
        WidthSpacer16()

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.title,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = LightPrimaryBlackColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            HeightSpacer4()
            Text(
                text = state.description,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = LightTernaryBlackColor,
                fontWeight = FontWeight.Normal,
                maxLines = 2
            )
        }

    }
}

@Preview(showSystemUi = false)
@Composable
fun PreviewClubItem() {
    ClubItem(
        state = ClubUIState(
            1, "Club Title", "Test Test TestTestTestTestTestTest Test"
        ),
        onClubSelected = {},
    )
}