package com.devfalah.ui.profile

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
import com.devfalah.ui.profile.profileSections.*
import com.devfalah.viewmodels.userProfile.UserProfileViewModel
import com.devfalah.viewmodels.userProfile.UserUIState

@Composable
fun ProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ProfileContent(state)
}

@Composable
fun ProfileContent(state: UserUIState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ProfileDetailsSection(state.userDetails) }
        item { FriendOptionsSection() }
        item { AlbumSection(state.albums) }
        item { FriendsSection(state.friends) }
        item { PostCreatingSection(state.userDetails) }
        items(state.posts) { ProfilePostItem(it) }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProfile() {
    ProfileScreen()
}