package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.profile.*
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun ProfilePostItem(
    post: PostUIState
) {
    Column(modifier = Modifier.fillMaxWidth()) {

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
                        text = "Friends",
                        fontSize = 12.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.SemiBold,
                        color = LightTernaryBlackColor
                    )
                    HorizontalSpacer8()

                    Image(
                        painter = painterResource(id = R.drawable.private_icon),
                        contentDescription = null,
                        modifier = Modifier.alignByBaseline()
                    )
                    HorizontalSpacer8()
                    Text(
                        text = " |  5 minutes ago",
                        fontSize = 12.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.SemiBold,
                        color = LightTernaryBlackColor
                    )
                }
            }
        }

        VerticalSpacer8()

        Text(
            text = post.postContent,
            modifier = Modifier.padding(horizontal = 16.dp),
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
        PostEnterAction(post)
    }
}

@Composable
fun PostEnterAction(
    post: PostUIState
) {
    VerticalSpacer16()

    Row(modifier = Modifier.fillMaxWidth()) {
        HorizontalSpacer16()
        Image(
            painter = painterResource(id = R.drawable.like_icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(LightTernaryBlackColor)
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
        Image(
            modifier = Modifier.weight(1f),
            alignment = Alignment.CenterEnd,
            painter = painterResource(id = R.drawable.save_icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(LightTernaryBlackColor)
        )
        HorizontalSpacer16()
    }
}

