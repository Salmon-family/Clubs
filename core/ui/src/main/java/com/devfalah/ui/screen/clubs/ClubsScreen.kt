package com.devfalah.ui.screen.clubs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.screen.clubs.composable.MyClubCard
import com.devfalah.viewmodels.myClubs.MyClubsState
import com.devfalah.viewmodels.myClubs.MyClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsViewModel

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel : MyClubsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ClubsContent(state = state, onClubClick = viewModel::onClubClicked)


}

@Composable
fun ClubsContent(
    state: MyClubsUiState,
    onClubClick: (MyClubsState) -> Unit
) {
    Log.i("test_Clubs", state.myClubs.toString())
}

@Preview
@Composable
private fun Preview() {
    ClubsScreen(navController = rememberNavController())
}
