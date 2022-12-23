package com.devfalah.ui.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor

@Composable
fun ButtonWithLoading(
    text: String,
    onClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    height: Dp = 48.dp,
    shape: Shape = RoundedCornerShape(100.dp),
    buttonColor: Color = LightPrimaryBrandColor,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        enabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = WhiteColor,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = WhiteColor,
                strokeWidth = 3.dp
            )
        } else {
            Text(
                text = text,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}