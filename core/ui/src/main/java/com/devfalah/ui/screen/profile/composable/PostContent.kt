package com.devfalah.ui.screen.profile.composable

import android.webkit.URLUtil
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.ExpandableText
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.util.htmlText
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun PostContent(
    post: PostUIState,
    contentExpandable: Boolean,
    maxLineToExpand: Int,
    onOpenLinkClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
) {

    if (URLUtil.isValidUrl(post.postContent)) {
        Text(
            modifier = Modifier
                .nonRippleEffect { onOpenLinkClick(post.postContent) }
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            text = post.postContent.htmlText(),
            color = LightPrimaryBrandColor,
            fontStyle = FontStyle.Italic,
            maxLines = 1,
        )
        ShowWebView(webUrl = post.postContent)
    } else {
        ExpandableText(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            text = if (post.isFromAlbum) {
                stringResource(id = R.string.change_profile)
            } else {
                post.postContent.htmlText()
            },
            minimizedMaxLines = if (contentExpandable) {
                maxLineToExpand
            } else {
                Int.MAX_VALUE
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondaryVariant,
                textDirection = TextDirection.Content
            )
        )
    }

    if (post.postImage.isNotBlank()) {
        if (post.postContent.isNotEmpty() || post.isFromAlbum) {
            HeightSpacer16()
        }
        Image(
            painter = rememberAsyncImagePainter(model = post.postImage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .nonRippleEffect { onImageClick(post.postImage) },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ShowWebView(
    modifier: Modifier = Modifier,
    webUrl: String
) {
    val state = rememberWebViewState(url = webUrl)
    WebView(
        state = state,
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        onCreated = {
            it.settings.loadWithOverviewMode = true
        }
    )
}

