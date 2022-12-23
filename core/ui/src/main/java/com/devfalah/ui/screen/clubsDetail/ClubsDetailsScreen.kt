package com.devfalah.ui.screen.clubsDetail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.RoundButton
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubsDetail.composable.ClubHeaderDetails
import com.devfalah.ui.screen.clubsDetail.composable.ClubMembers
import com.devfalah.ui.screen.clubsDetail.composable.OutlineButton
import com.devfalah.ui.screen.clubsDetail.composable.PrivateClubsBox
import com.devfalah.ui.screen.friends.navigateToFriends
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.Constants
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState
import com.devfalah.viewmodels.clubDetails.ClubDetailsViewModel
import com.devfalah.viewmodels.friendRequest.UserState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ClubsDetailsScreen(
    navController: NavController,
    viewModel: ClubDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    ClubsDetailsContent(
        state = state,
        onBack = { navController.popBackStack() },
        onRefresh = viewModel::swipeToRefresh,
        onClickLike = viewModel::onClickLike,
        onClickComment = { },
        onClickSave = viewModel::onClickSave,
        onAddPost = { },
        onClickFriends = { navController.navigateToFriends(it) },
        onJoinClub = viewModel::joinClubs,
        onUnJoinClubs = viewModel::unJoinClubs,
        onDeclineClub = viewModel::declineRequestOfClub,
        isMyPost = viewModel::isMyPost,
        onRetry = viewModel::getData
    )
}

@Composable
private fun ClubsDetailsContent(
    state: ClubDetailsUiState,
    onBack: () -> Unit,
    onRefresh: (Int) -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onAddPost: () -> Unit,
    onClickFriends: (Int) -> Unit,
    onJoinClub: () -> Unit,
    onUnJoinClubs: () -> Unit,
    onDeclineClub: () -> Unit,
    isMyPost: (Int) -> Boolean,
    onRetry: () -> Unit
) {

    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        if (state.errorMessage.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize())
            Button(
                onClick = onRetry
            ) {
                Text(text = stringResource(id = R.string.retry))
            }
        } else {
            ManualPager(
                onRefresh = onRefresh,
                isLoading = state.isLoading,
                error = state.pagerError,
                isEndOfPager = state.isEndOfPager,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                item {
                    ClubHeaderDetails(
                        state = state,
                        onBack = onBack,
                    )
                }

                if (state.privacy != Constants.PUBLIC_PRIVACY && !state.isMember) {
                    if (state.requestExists) {
                        item {
                            RoundButton(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                userState = UserState(),
                                text = stringResource(id = R.string.request_to_join),
                                textColor = WhiteColor,
                                onButtonClick = onUnJoinClubs,
                            )
                        }
                    } else {
                        item {
                            RoundButton(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                userState = UserState(),
                                text = stringResource(id = R.string.join_club),
                                textColor = WhiteColor,
                                onButtonClick = onJoinClub,
                            )
                        }
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
                                .padding(horizontal = 16.dp),
                            onClick = onDeclineClub
                        )

                    }

                    item {
                        PostCreatingSection(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onCreatePost = onAddPost,
                            isMyProfile = true
                        )
                    }

                    item {
                        ClubMembers(friends = state.members,
                            modifier = Modifier
                                .nonRippleEffect { onClickFriends(state.clubId) }
                                .padding(horizontal = 16.dp))
                    }

                    items(state.posts) {
                        PostItem(
                            state = it,
                            isMyPost = isMyPost.invoke(it.postId),
                            isContentExpandable = true,
                            onClickLike = onClickLike,
                            onClickComment = onClickComment,
                            onClickSave = { onClickSave(it) },
                            onClickPostSetting = {},
                            onClickProfile = {},
                            onOpenLinkClick = {},
                        )
                    }
                } else {
                    if (state.requestExists) {
                        item {
                            RoundButton(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                userState = UserState(),
                                text = stringResource(id = R.string.request_to_join),
                                textColor = WhiteColor,
                                onButtonClick = onUnJoinClubs,
                            )
                        }
                    } else {
                        item {
                            RoundButton(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                userState = UserState(),
                                text = stringResource(id = R.string.join_club),
                                textColor = WhiteColor,
                                onButtonClick = onJoinClub,
                            )
                        }
                    }

                    item {
                        ClubMembers(friends = state.members,
                            modifier = Modifier
                                .nonRippleEffect { onClickFriends(state.clubId) }
                                .padding(horizontal = 16.dp))
                    }

                    items(state.posts) {
                        PostItem(
                            state = it,
                            isMyPost = isMyPost.invoke(it.postId),
                            isContentExpandable = true,
                            onClickLike = onClickLike,
                            onClickComment = onClickComment,
                            onClickSave = { onClickSave(it) },
                            onClickPostSetting = {},
                            onClickProfile = {},
                            onOpenLinkClick = {},
                        )
                    }
                }
            }

        }
    }

    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage.isNotEmpty()) {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightPrimaryBrandColor,
            darkIcons = false
        )
    }
}
