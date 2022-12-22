package com.devfalah.ui.screen.clubs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.screen.clubCreation.navigateToClubCreation
import com.devfalah.ui.screen.clubRequests.navigateToClubRequests
import com.devfalah.ui.screen.editclubscreen.navigateToEditClub

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ClubsScreen(
    navController: NavController,
) {

    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.clubs), navHostController =navController)
        }
    ) {
      Column {
          Button(onClick = {
              navController.navigateToClubCreation()
          }) {

          }
          Button(onClick = { navController.navigateToClubRequests(clubId = 256, ownerId = 6) }) {
              Text(text = "Requests")
          }

          Button(onClick = { navController.navigateToEditClub(
              clubId = 56,
              clubDescription = "bb",
              clubName = "ooooooo",
              clubPrivacy = 2
          ) }) {
              Text(text = "Edit")
          }
      }
    }
}