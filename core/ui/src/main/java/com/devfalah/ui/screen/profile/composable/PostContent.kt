package com.devfalah.ui.screen.profile.composable

import android.webkit.URLUtil
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.ExpandText
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
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
    isPostDetails: Boolean = false
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
        ExpandText(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = post.postContent.htmlText(),
            isPostDetails = isPostDetails
        )
    }

    if (post.postImage.isNotBlank()) {
        if (post.postContent.isNotEmpty()) {
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

