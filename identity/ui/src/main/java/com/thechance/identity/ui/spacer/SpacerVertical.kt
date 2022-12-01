package com.thechance.identity.ui.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerVertical(height: Dp){
    Spacer(modifier = Modifier.height(height))
}