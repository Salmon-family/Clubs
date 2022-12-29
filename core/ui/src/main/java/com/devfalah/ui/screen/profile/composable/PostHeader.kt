package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.util.getDataDescription
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostHeader(
    post: PostUIState,
    isMyProfile: Boolean,
    hidePrivacy: Boolean,
    showGroupName: Boolean,
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
                color = MaterialTheme.colors.primaryVariant
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!hidePrivacy) {
                    Icon(
                        painter = getPrivacyIcon(post.privacy),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 4.dp),
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                    WidthSpacer8()
                    Text(
                        text = "${getPrivacyText(post.privacy)}  | ",
                        fontSize = 12.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onSecondary,
                    )

                    WidthSpacer8()
                }
                if (hidePrivacy && showGroupName) {
                    Text(
                        text = "${post.groupName}  | ",
                        fontSize = 12.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onSecondary,
                    )

                    WidthSpacer8()
                }
                Text(
                    text = "${post.createdData.value} ${getDataDescription(post.createdData.description)} ",
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }

        if (isMyProfile) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.nonRippleEffect { expanded = true },
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.background),
                offset = DpOffset(235.dp, (-18).dp)
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

