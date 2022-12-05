package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostHeader(post: PostUIState) {
    Row(modifier = Modifier.fillMaxWidth()) {
        WidthSpacer16()
        Image(
            painter = rememberAsyncImagePainter(model = post.posterImage),
            contentDescription = null,
            Modifier
                .size(40.dp)
                .clip(CircleShape),
        )
        WidthSpacer8()
        Column {
            Text(
                text = post.posterName,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                color = LightPrimaryBlackColor
            )
            Row {
                Text(
                    text = getPrivacyText(post.privacy),
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    color = LightTernaryBlackColor
                )
                WidthSpacer8()

                Image(
                    painter = getPrivacyIcon(post.privacy),
                    contentDescription = null,
                    modifier = Modifier.alignByBaseline()
                )
                WidthSpacer8()
                Text(
                    text = " |  ${post.createdData}",
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    color = LightTernaryBlackColor
                )
            }
        }
    }
}

@Composable
private fun getPrivacyText(privacy: Boolean): String {
    return if (privacy) {
        stringResource(id = R.string.friends_privacy)
    } else {
        stringResource(id = R.string.public_privacy)
    }
}

@Composable
private fun getPrivacyIcon(privacy: Boolean): Painter {
    return if (privacy) {
        painterResource(id = R.drawable.private_icon)
    } else {
        painterResource(id = R.drawable.public_icon)
    }
}

