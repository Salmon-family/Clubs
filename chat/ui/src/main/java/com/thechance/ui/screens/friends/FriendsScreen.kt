package com.thechance.ui.screens.friends

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thechance.ui.composable.Loading
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
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopAppBarFriends(
                onClickBack
            )
        }
    ) {
        if (uiState.isLoading) {
            Loading()
        }
        if (!uiState.isLoading && uiState.isEmpty()) {
            EmptyState(modifier = Modifier.fillMaxSize())
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,
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