package com.devfalah.ui.screen.menu.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color = LightCardBackgroundColor,
    text: String,
    textColor: Color = LightPrimaryBlackColor,
    iconModifier: Modifier = Modifier,
    painter: Painter,
    iconSize: Dp = 24.dp,
    iconColor: Color = LightSecondaryBlackColor,
    onClickItem: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(backgroundColor)
            .clickable { onClickItem() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        )
        Icon(
            modifier = iconModifier.size(iconSize),
            painter = painter,
            tint = iconColor,
            contentDescription = null
        )
    }
}