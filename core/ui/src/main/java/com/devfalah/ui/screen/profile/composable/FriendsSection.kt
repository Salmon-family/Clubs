package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.FriendUIState

@Composable
fun FriendsSection(
    friends: List<FriendUIState>,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
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
                text = "${friends.size} ${stringResource(id = R.string.friends)}",
                fontFamily = PlusJakartaSans,
                color = LightTernaryBlackColor,
                fontSize = 12.sp
            )
        }

        HeightSpacer8()

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            friends.take(4).forEach {
                Friend(
                    painter = rememberAsyncImagePainter(model = it.profileImageUrl),
                    text = it.name,
                )
            }

            Friend(
                painter = painterResource(id = R.drawable.ic_more),
                text = stringResource(R.string.more),
                textColor = LightPrimaryBrandColor
            )
        }

    }
}

@Composable
fun Friend(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    textColor: Color = LightPrimaryBlackColor
) {
    Column(
        modifier = Modifier.width(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        HeightSpacer8()

        Text(
            text = text,
            color = textColor,
            fontFamily = PlusJakartaSans,
            fontSize = 12.sp
        )
    }
}
