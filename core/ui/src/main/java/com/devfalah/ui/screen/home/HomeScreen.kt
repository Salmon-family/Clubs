package com.devfalah.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun HomeScreen(
    navController: NavController,
    ) {
    Text(text = "Home", fontSize = 24.sp)
}
