package com.devfalah.ui.screen.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.spacer.HorizontalSpacer16
import com.devfalah.ui.spacer.HorizontalSpacer8
import com.devfalah.ui.spacer.VerticalSpacer16
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

@Composable
fun PostCreatingSection(
    user: UserDetailsUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(LightCardBackgroundColor)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(model = user.profilePicture),
                contentDescription = null,
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
            HorizontalSpacer16()
            Text(
                text = stringResource(R.string.what_are_you_thinking_about),
                fontFamily = PlusJakartaSans,
                color = LightSecondaryBlackColor
            )
        }

        VerticalSpacer16()
        Divider(color = Color.White, thickness = 1.dp)
        VerticalSpacer16()

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            PostOption(imagePainter = R.drawable.ic_photo, text = stringResource(R.string.photo))
            PostOption(imagePainter = R.drawable.ic_tag, text = stringResource(R.string.tag))
            PostOption(
                imagePainter = R.drawable.ic_location,
                text = stringResource(R.string.location)
            )
            PostOption(imagePainter = R.drawable.ic_color, text = stringResource(R.string.color))
        }

    }
}

@Composable
fun PostOption(
    modifier: Modifier = Modifier,
    imagePainter: Int,
    text: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imagePainter),
            contentDescription = null,
            Modifier.size(24.dp)
        )
        HorizontalSpacer8()
        Text(
            text = text,
            fontFamily = PlusJakartaSans,
            color = LightTernaryBlackColor,
            fontSize = 12.sp
        )
    }
}
