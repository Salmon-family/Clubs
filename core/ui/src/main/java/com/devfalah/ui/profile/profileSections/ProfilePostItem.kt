package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.profile.HorizontalSpacer16
import com.devfalah.ui.profile.HorizontalSpacer24
import com.devfalah.ui.profile.HorizontalSpacer8
import com.devfalah.ui.profile.VerticalSpacer16
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun ProfilePostItem(
    post: PostUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PostHeader(post)
        PostContent(post)
        PostEnterAction(post, onClickLike, onClickComment, onClickSave)
    }
}

@Composable
fun PostHeader(post: PostUIState) {
    Row(modifier = Modifier.fillMaxWidth()) {
        HorizontalSpacer16()
        Image(
            painter = rememberAsyncImagePainter(model = post.posterImage),
            contentDescription = null,
            Modifier
                .size(40.dp)
                .clip(CircleShape),
        )
        HorizontalSpacer8()
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
                HorizontalSpacer8()

                Image(
                    painter = getPrivacyIcon(post.privacy),
                    contentDescription = null,
                    modifier = Modifier.alignByBaseline()
                )
                HorizontalSpacer8()
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
fun PostContent(post: PostUIState) {
    Text(
        text = post.postContent,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        fontSize = 14.sp,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        color = LightSecondaryBlackColor
    )

    if (post.postImage.isNotBlank()) {
        VerticalSpacer16()
        Image(
            painter = rememberAsyncImagePainter(model = post.postImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PostEnterAction(
    post: PostUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
) {
    VerticalSpacer16()

    Row(modifier = Modifier.fillMaxWidth()) {
        HorizontalSpacer16()
        Image(
            modifier = Modifier.clickable { onClickLike(post) },
            painter = painterResource(
                id = if (post.isLikedByUser) {
                    R.drawable.heart_full
                } else {
                    R.drawable.like_icon
                }
            ),
            contentDescription = null
        )
        HorizontalSpacer8()
        if (post.totalLikes > 0) {
            Text(
                text = "${post.totalLikes}",
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = LightTernaryBlackColor
            )
        }
        HorizontalSpacer24()

        Image(
            modifier = Modifier.clickable { onClickComment(post) },
            painter = painterResource(id = R.drawable.comment_icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(LightTernaryBlackColor)
        )
        HorizontalSpacer8()
        if (post.totalComments > 0) {
            Text(
                text = "${post.totalComments}",
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = LightTernaryBlackColor
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .clickable { onClickSave(post) },
                alignment = Alignment.CenterEnd,
                painter = painterResource(
                    id = if (post.isSaved) {
                        R.drawable.save_full
                    } else {
                        R.drawable.save_icon
                    }
                ),
                contentDescription = null,
            )
        }
        HorizontalSpacer16()
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

