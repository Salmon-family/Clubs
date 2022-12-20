package com.devfalah.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
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
    shape: Shape = RoundedCornerShape(100.dp),
    buttonColor: Color = LightPrimaryBrandColor,
){
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = WhiteColor,
        ),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = WhiteColor)
        } else {
            Text(
                text = text,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

