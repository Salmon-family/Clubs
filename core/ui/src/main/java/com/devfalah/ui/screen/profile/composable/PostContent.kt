package com.devfalah.ui.screen.profile.composable

import android.webkit.URLUtil
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.devfalah.ui.composable.ExpandableText
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
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
    onOpenLinkClick: (String) -> Unit
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
            text = post.postContent.htmlText(),
            minimizedMaxLines = if (contentExpandable) {
                maxLineToExpand
            } else {
                Int.MAX_VALUE
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondaryVariant
            )
        )
    }

    if (post.postImage.isNotBlank()) {
        if (post.postContent.isNotEmpty()) {
            HeightSpacer16()
        }
        ZoomableImage(
            painter = rememberAsyncImagePainter(model = post.postImage),
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun ZoomableImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentScale: ContentScale = ContentScale.Fit,

    ) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

        Image(
            painter = painter,
            contentDescription = "A Content description",
            modifier = modifier
                .clipToBounds()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,

                )
                .pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { _, pan: Offset, zoom: Float, _ ->
                            offset += pan
                            scale = (scale * zoom).coerceIn(1f, 2f)
                        }
                    )
                },
            contentScale = contentScale
        )
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
//            it.settings.javaScriptEnabled = true
            it.settings.loadWithOverviewMode = true
        }
    )
}

