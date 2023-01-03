package com.thechance.ui.screens.friends

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thechance.ui.composable.Loading
import com.thechance.ui.composable.SearchTextField
import com.thechance.ui.screens.chat.isScrolledToTheEnd
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
        onValueChanged = viewModel::onSearchTextChanged,
        onClickBack = { navController.popBackStack() },
        onLoadingMoreFriends = viewModel::onLoadingMoreFriends,
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
    onValueChanged: (String) -> Unit,
    onClickBack: () -> Unit,
    onLoadingMoreFriends: () -> Unit,
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
            item {
                SearchTextField(
                    text = uiState.searchQuery,
                    onValueChanged = onValueChanged
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
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
    LaunchedEffect(key1 = listState.isScrolledToTheEnd()) {
        if (!uiState.isLoadingMore && !uiState.isLastPage && listState.isScrolledToTheEnd()) {
            onLoadingMoreFriends()
        }
    }

}

fun Modifier.nonRippleEffect(onClick: () -> Unit) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() }, indication = null) {
        onClick()
    }
}