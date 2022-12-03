package com.devfalah.ui.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer8() {
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun HorizontalSpacer16() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun HorizontalSpacer24() {
    Spacer(modifier = Modifier.width(24.dp))
}

@Composable
fun VerticalSpacer16() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun VerticalSpacer14() {
    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
fun VerticalSpacer5() {
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun VerticalSpacer8() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun VerticalSpacer4() {
    Spacer(modifier = Modifier.height(4.dp))
}