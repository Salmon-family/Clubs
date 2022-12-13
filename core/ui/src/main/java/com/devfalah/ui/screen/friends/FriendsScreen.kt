package com.devfalah.ui.screen.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.FriendItem
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.friends.FriendsUIState
import com.devfalah.viewmodels.friends.FriendsViewModel

@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    SetStatusBarColor(LightBackgroundColor, darkIcons = true)

    val state by viewModel.uiState.collectAsState()
    FriendsContent(
        state = state,
        onClickProfile = { navController.navigateToProfile(it) }
    )
}

@Composable
fun FriendsContent(
    state: FriendsUIState,
    onClickProfile: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(LightBackgroundColor)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "${state.friends.size} " + stringResource(id = R.string.friends),
                fontFamily = PlusJakartaSans,
                fontSize = 18.sp,
                color = LightSecondaryBlackColor,
                fontWeight = FontWeight.SemiBold
            )
        }
        items(state.friends) {
            FriendItem(
                state = it,
                onOpenProfileClick = onClickProfile
            )
        }
    }
}
