package com.thechance.ui.screens.friends

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thechance.ui.R
import com.thechance.ui.composable.ErrorItem
import com.thechance.ui.composable.Loading
import com.thechance.ui.composable.ManualPager
import com.thechance.ui.screens.conversation.navigateToConversation
import com.thechance.viewmodels.friends.FriendsViewModel
import com.thechance.viewmodels.friends.uistate.FriendsUiState
import com.thechance.viewmodels.friends.uistate.isEmpty

@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    FriendsContent(
        uiState = state,
        onClickFriend = {
            navController.navigateToConversation(it)
        },
        onClickBack = { navController.popBackStack() },
        onRefresh = viewModel::getFriendsList,
        onRetry = viewModel::getFriendsList
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        systemUIController.setStatusBarColor(color)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun FriendsContent(
    uiState: FriendsUiState,
    onClickFriend: (Int) -> Unit,
    onClickBack: () -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
) {
    Column {
        TopAppBarFriends(onClickBack)
        if (uiState.isLoading) {
            Loading()
        } else if (uiState.isFail) {
            ErrorItem(onClickRetry = onRetry)
        } else if (uiState.isEmpty()) {
            EmptyState(modifier = Modifier.fillMaxSize())
        }

        ManualPager(
            onRefresh = onRefresh,
            contentPadding = PaddingValues(16.dp),
            isLoading = uiState.isPagerLoading,
            error = if (uiState.isPagerFail) stringResource(R.string.an_error_has_occurred) else "",
            isEndOfPager = uiState.isPagerEnd,
        ) {

            items(
                items = uiState.friends,
                key = { friendUiState ->
                    friendUiState.id
                }
            ) { friend ->
                FriendItem(
                    modifier = Modifier.animateItemPlacement(),
                    state = friend,
                    onClickNewFriend = onClickFriend
                )
            }
        }


    }

}