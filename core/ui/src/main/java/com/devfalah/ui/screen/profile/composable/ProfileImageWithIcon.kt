package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBrandColor

@Composable
fun ProfileImageWithIcon(
    profilePicture: String,
    onClickIcon: () -> Unit,
    painter: Painter?
) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(model = profilePicture),
            contentDescription = null,
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape),
        )

        painter?.let {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, top = 80.dp)
                    .size(40.dp)
                    .nonRippleEffect { onClickIcon() }
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(LightSecondaryBrandColor)
                    .border(4.dp, Color.White, CircleShape)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    tint = LightPrimaryBrandColor,
                    painter = painter,
                    contentDescription = null
                )

            }
        }
    }
}


@Composable
@Preview
fun ProfilePreview() {
    ProfileImageWithIcon(
        "https://test.the-chance.org/avatar/nadaFeteiha/large/88ec93c152dfefbb292d66c901e8b542.jpeg",
        {},
        painterResource(id = R.drawable.ic_add_user)
    )
}