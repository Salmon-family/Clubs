package com.devfalah.ui.screen.notification.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.util.Constants

@Composable
fun NotificationTitle(type: Int, posterName: String) {

    val notificationType = when (type) {
        Constants.LIKE_PHOTO -> stringResource(id = R.string.like_photo)
        Constants.LIKE_POST -> stringResource(id = R.string.like_post)
        Constants.REQUEST_GROUP -> stringResource(id = R.string.request_group)
        Constants.COMMENT_POST -> stringResource(R.string.commented_on_your_post)
        Constants.LIKE_COMMENT_POST -> stringResource(R.string.liked_your_comment_on_a_post)
        else -> ""
    }

    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = PlusJakartaSans)
            ) {
                append(posterName)
            }
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Normal, fontFamily = PlusJakartaSans)
            ) {
                append(" $notificationType")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        lineHeight = 20.sp,
        color = MaterialTheme.colors.primaryVariant
    )
}
