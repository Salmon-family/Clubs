package com.devfalah.ui.screen.clubs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.screen.clubs.composable.MyClubCard

@Composable
fun ClubsScreen(
    navController: NavController,
) {
    MyClubCard()

}

@Preview
@Composable
private fun Preview() {
    ClubsScreen(navController = rememberNavController())
}
