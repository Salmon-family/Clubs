package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostHeader(
    post: PostUIState,
    isMyProfile: Boolean,
    onClickPostSetting: (PostUIState) -> Unit,
    onClickProfile: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        WidthSpacer16()
        Image(
            painter = rememberAsyncImagePainter(model = post.publisherImage),
            contentDescription = null,
            Modifier
                .nonRippleEffect { onClickProfile(post.publisherId) }
                .size(40.dp)
                .clip(CircleShape),
        )
        WidthSpacer8()
        Column {
            Text(
                text = post.publisherName,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                color = LightPrimaryBlackColor
            )
            Row {
                Icon(
                    painter = getPrivacyIcon(post.privacy),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 4.dp)
                )
                WidthSpacer8()
                Text(
                    text = getPrivacyText(post.privacy),
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    color = LightTernaryBlackColor,
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

        if (isMyProfile) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.nonRippleEffect { expanded = true },
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(WhiteColor),
                offset = DpOffset(250.dp, 0.dp)
            ) {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        expanded = false
                        onClickPostSetting(post)
                    }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.delete),
                        textAlign = TextAlign.Center
                    )
                }
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
        painterResource(id = R.drawable.ic_friends)
    } else {
        painterResource(id = R.drawable.public_icon)
    }
}

