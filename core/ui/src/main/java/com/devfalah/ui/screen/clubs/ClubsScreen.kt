package com.devfalah.ui.screen.clubs

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.screen.clubCreation.navigateToClubCreation

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
       Button(onClick = {
           navController.navigateToClubCreation()
       }) {

       }
    }
}
