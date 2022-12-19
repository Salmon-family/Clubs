package com.devfalah.ui.screen.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.devfalah.ui.screen.clubs.composable.MyClubCard
import com.devfalah.ui.screen.clubs.composable.SearchItem
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.viewmodels.myClubs.ClubsState
import com.devfalah.viewmodels.myClubs.ClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsViewModel

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel : MyClubsViewModel = hiltViewModel()
) {
    val state by viewModel.clubsUiState.collectAsState()
    ClubsContent(state = state, onClubClick = viewModel::onClubClicked)


}

@Composable
fun ClubsContent(
    state: ClubsUiState,
    onClubClick: (ClubsState) -> Unit
) {
    
    MyClubsScreen(state = state , onClubClick = onClubClick)
}

@Composable
fun MyClubsScreen(
    state: ClubsUiState,
    onClubClick: (ClubsState) -> Unit
    ){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(LightCardBackgroundColor),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item {
            SearchItem(onSearchItemClick = {})
        }

        items(state.clubs){
            MyClubCard(myClub = it, onClubClick = onClubClick)
        }
    }
}

@Composable
fun TopClubsScreen(
    state: MyClubsUiState,
    onClubClick: (ClubsState) -> Unit
){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(LightCardBackgroundColor),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item {
            SearchItem(onSearchItemClick = {})
        }


        items(state.myClubs){
            MyClubCard(myClub = it, onClubClick = onClubClick)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ClubsScreen(navController = rememberNavController())
}
