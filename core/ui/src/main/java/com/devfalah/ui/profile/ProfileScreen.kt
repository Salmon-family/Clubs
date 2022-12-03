package com.devfalah.ui.profile

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.profile.profileSections.*
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.UserProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState

@Composable
fun ProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ProfileContent(
        state,
        onClickLike = viewModel::onClickLike,
        onClickComment = viewModel::onClickComment,
        onClickSave = viewModel::onClickSave
    )
}

@Composable
fun ProfileContent(
    state: UserUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
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
        item { FriendOptionsSection(modifier = Modifier.padding(horizontal = 16.dp)) }
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

@Preview(showSystemUi = true)
@Composable
fun PreviewProfile() {
    ProfileScreen()
}