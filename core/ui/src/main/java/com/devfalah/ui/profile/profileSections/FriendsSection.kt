package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.profile.VerticalSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun FriendsSection() {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.friends),
                fontFamily = PlusJakartaSans,
                color = LightPrimaryBlackColor,
                fontSize = 14.sp
            )
            Text(
                text = "40 friends",
                fontFamily = PlusJakartaSans,
                color = LightTernaryBlackColor,
                fontSize = 12.sp
            )
        }

        VerticalSpacer8()

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val friends = listOf("Ali", "Tariq", "Noor", "Alex", "Muhammed", "Hadi")
            friends.take(4).forEach {
                Friend(
                    imagePainter = R.drawable.test_image,
                    text = it,
                )
            }

            Friend(
                imagePainter = R.drawable.ic_more,
                text = stringResource(R.string.more),
                textColor = LightPrimaryBrandColor
            )
        }

    }
}

@Composable
fun Friend(
    modifier: Modifier = Modifier,
    imagePainter: Int,
    text: String,
    textColor: Color = LightPrimaryBlackColor
) {
    Column(
        modifier = Modifier.width(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagePainter),
            contentDescription = null,
            modifier = modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        VerticalSpacer8()

        Text(
            text = text,
            color = textColor,
            fontFamily = PlusJakartaSans,
            fontSize = 12.sp
        )
    }
}


@Preview
@Composable
fun PreviewFriends() {
    FriendsSection()
}