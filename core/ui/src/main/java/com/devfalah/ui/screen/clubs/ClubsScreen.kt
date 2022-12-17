package com.devfalah.ui.screen.clubs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar

@Composable
fun ClubsScreen(
    navController: NavController,
) {
    AppBar(title = stringResource(R.string.clubs), navHostController =navController)
}
