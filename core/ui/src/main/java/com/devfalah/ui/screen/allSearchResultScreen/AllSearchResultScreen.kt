package com.devfalah.ui.screen.allSearchResultScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.ClubItem
import com.devfalah.ui.composable.FriendItem
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.allSearchResult.AllSearchResultUIState
import com.devfalah.viewmodels.allSearchResult.AllSearchResultViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun AllSearchResultScreenScreen(
    navController: NavController,
    viewModel: AllSearchResultViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current

    AllSearchResultScreenContent(
        state = state,
        onClubSelected = {
            Toast.makeText(context, "Should Navigate to Club Id = $it", Toast.LENGTH_LONG).show()
        },
        OnUserClick = { navController.navigateToProfile(it) },
    )

    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController, color = LightBackgroundColor, darkIcons = true
        )
    }
}

@Composable
fun AllSearchResultScreenContent(
    state: AllSearchResultUIState,
    onClubSelected: (Int) -> Unit,
    OnUserClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .background(LightBackgroundColor)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item("title") {
            Text(
                text = state.title,
                fontSize = 18.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                color = LightPrimaryBlackColor
            )
        }

        if (state.isClub) {
            items(
                items = state.clubs,
                key = { "${it.id} ${it.title}" }
            ) { club ->
                ClubItem(state = club, onClubSelected = onClubSelected)
            }
        } else {
            items(
                items = state.users,
                key = { it.id }
            ) { user ->
                FriendItem(state = user, onOpenProfileClick = OnUserClick)
            }
        }
    }
}
