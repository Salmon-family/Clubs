package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun EmptyScreenTexts(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        )

        HeightSpacer4()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = description,
            style = TextStyle(
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}