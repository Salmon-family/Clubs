package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor
import kotlin.math.roundToInt

@Composable
fun PostCreatingSection(
    modifier: Modifier = Modifier,
    onCreatePost: () -> Unit
) {
    SwipeButton(
        modifier = modifier,
        text = "Share what you love",
        onCreatePost = onCreatePost
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = WhiteColor,
    onCreatePost: () -> Unit,
) {
    val width = 250.dp
    val widthInPx = with(LocalDensity.current) {
        width.toPx()
    }
    val anchors = mapOf(0F to 0, widthInPx to 1)
    val swappableState = rememberSwipeableState(0)
    val (swipeComplete, setSwipeComplete) = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(
        key1 = swappableState.currentValue,
    ) {
        if (swappableState.currentValue == 1) {
            setSwipeComplete(true)
            onCreatePost()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(backgroundColor)
            .requiredHeight(64.dp),
    ) {
        SwipeIndicator(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(swappableState.offset.value.roundToInt(), 0)
                }
                .swipeable(
                    state = swappableState,
                    anchors = anchors,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.3F)
                    },
                    orientation = Orientation.Horizontal,
                ),
            backgroundColor = backgroundColor,
        )
        Text(
            text = text,
            color = LightPrimaryBrandColor,
            fontSize = 14.sp,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(swappableState.offset.value.roundToInt(), 0) },
        )
    }
}

@Composable
fun SwipeIndicator(
    modifier: Modifier = Modifier,
    backgroundColor: Color = LightPrimaryBrandColor,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .padding(2.dp)
            .clip(CircleShape)
            .aspectRatio(
                ratio = 1.0F,
                matchHeightConstraintsFirst = true,
            )
            .background(LightPrimaryBrandColor),
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            tint = backgroundColor,
            modifier = Modifier.size(36.dp),
        )
    }
}