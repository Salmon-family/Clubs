package com.devfalah.ui.screen.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.screen.profile.profileSections.*
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.ProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    ProfileContent(
        state,
        onClickLike = viewModel::onClickLike,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave,
        onClickAddFriend = viewModel::onClickAddFriend,
        onClickSendMessage = {
            Toast.makeText(context, "not done yet.. ", Toast.LENGTH_LONG).show()
        }
    )
    if (state.minorError.isNotEmpty()) {
        ShowErrorMessage(state.minorError, context)
    }
}

@Composable
fun ProfileContent(
    state: UserUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickAddFriend: () -> Unit,
    onClickSendMessage: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileDetailsSection(
                state.userDetails,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        if (!state.isMyProfile) {
            item {
                FriendOptionsSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    areFriends = state.areFriends,
                    onClickAddFriend = onClickAddFriend,
                    onClickSendMessage = onClickSendMessage
                )
            }
        }
        item { AlbumSection(state.albums, modifier = Modifier.padding(horizontal = 16.dp)) }
        item { FriendsSection(state.friends, modifier = Modifier.padding(horizontal = 16.dp)) }
        item {
            PostCreatingSection(
                state.userDetails,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(state.posts) {
            ProfilePostItem(
                it,
                onClickLike = { onClickLike(it) },
                onClickComment = { onClickComment(it) },
                onClickSave = { onClickSave(it) }
            )
        }
    }
}


@Composable
fun ShowErrorMessage(error: String, context: Context) {
    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
}