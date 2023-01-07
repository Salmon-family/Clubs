package com.devfalah.ui.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun ExpandText(
    modifier: Modifier = Modifier,
    text: String,
    minimumLines: Int = 2,
    isPostDetails: Boolean = false,
    style: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colors.secondaryVariant,
        textDirection = TextDirection.Content,
    )
) {
    var showMore by remember { mutableStateOf(isPostDetails) }
    Column(
        modifier = modifier.fillMaxWidth()
            .animateContentSize(animationSpec = tween(100))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showMore = !showMore }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            maxLines = if (showMore) Int.MAX_VALUE else minimumLines,
            overflow = TextOverflow.Ellipsis,
            style = style
        )
    }
}