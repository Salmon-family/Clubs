package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostContent(post: PostUIState, contentExpandable: Boolean, maxLineToExpand: Int)
{
    ExpandableText(
        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp),
        text = post.postContent,
        minimizedMaxLines = if (contentExpandable){maxLineToExpand}else{ Int.MAX_VALUE},
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
            color = LightSecondaryBlackColor
        )
    )

    if (post.postImage.isNotBlank()) {
        if (post.postContent.isNotEmpty()) {
            HeightSpacer16()
        }
        Image(
            painter = rememberAsyncImagePainter(model = post.postImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

