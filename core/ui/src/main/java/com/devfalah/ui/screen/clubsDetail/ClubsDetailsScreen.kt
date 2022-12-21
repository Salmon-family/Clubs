package com.devfalah.ui.screen.clubsDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.RoundButton
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubsDetail.composable.ClubHeaderDetails
import com.devfalah.ui.screen.clubsDetail.composable.ClubMembers
import com.devfalah.ui.screen.clubsDetail.composable.OutlineButton
import com.devfalah.ui.screen.clubsDetail.composable.PrivateClubsBox
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState
import com.devfalah.viewmodels.clubDetails.ClubDetailsViewModel
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun ClubsDetailsScreen(
    navController: NavController,
    viewModel: ClubDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    ClubsDetailsContent(
        state = state,
        onBack = { navController.popBackStack() },
        onRetry = viewModel::getData
    )
}

@Composable
private fun ClubsDetailsContent(
    state: ClubDetailsUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.errorMessage.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize())
            Button(
                onClick = onRetry
            ) {
                Text(text = "Retry")
            }
        } else {
            ManualPager(
                onRefresh = { onBack() },
                isLoading = state.isLoading,
                error = state.errorMessage,
                isEndOfPager = state.privacy == "Public",
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                item {
                    ClubHeaderDetails(
                        state = state,
                        onBack = onBack
                    )
                }

                if (state.privacy != "Public") {

                    item {
                        RoundButton(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            userState = UserState(),
                            text = stringResource(id = R.string.request_to_join),
                            textColor = WhiteColor,
                            onButtonClick = {}
                        )

                    }

                    item {
                        PrivateClubsBox(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                        )
                    }

                } else if (state.isMember) {
                    item {
                        OutlineButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .background(color = LightBackgroundColor)
                        )
                    }

                    item {
                        ClubMembers(
                            friends = state.members,
                            modifier = Modifier
                                .nonRippleEffect { }
                                .padding(horizontal = 16.dp)
                        )
                    }

                    items(state.posts) {
                        PostItem(
                            state = it,
                            isMyPost = true,
                            isContentExpandable = true,
                            onClickLike = {},
                            onClickComment = {},
                            onClickSave = {},
                            onClickPostSetting = {},
                            onClickProfile = {},
                            onOpenLinkClick = {},
                        )
                    }
                } else {
                    item {
                        RoundButton(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            userState = UserState(),
                            text = stringResource(id = R.string.join_club),
                            textColor = WhiteColor,
                            onButtonClick = {}
                        )
                    }

                    item {
                        ClubMembers(
                            friends = state.members,
                            modifier = Modifier
                                .nonRippleEffect { }
                                .padding(horizontal = 16.dp)
                        )
                    }

                    items(state.posts) {
                        PostItem(
                            state = it,
                            isMyPost = true,
                            isContentExpandable = true,
                            onClickLike = {},
                            onClickComment = {},
                            onClickSave = {},
                            onClickPostSetting = {},
                            onClickProfile = {},
                            onOpenLinkClick = {},
                        )
                    }
                }
            }

        }
    }
}