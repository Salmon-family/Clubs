package com.devfalah.ui.screen.clubsDetail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import com.devfalah.ui.composable.*
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubMembers.navigateToMembers
import com.devfalah.ui.screen.clubRequests.navigateToClubRequests
import com.devfalah.ui.screen.clubsDetail.composable.ClubHeaderDetails
import com.devfalah.ui.screen.clubsDetail.composable.ClubMembers
import com.devfalah.ui.screen.clubsDetail.composable.OutlineButton
import com.devfalah.ui.screen.clubsDetail.composable.PrivateClubsBox
import com.devfalah.ui.screen.editclubscreen.navigateToEditClub
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState
import com.devfalah.viewmodels.clubDetails.ClubDetailsViewModel
import com.devfalah.viewmodels.friendRequest.UserState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.util.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ClubsDetailsScreen(
    navController: NavController,
    viewModel: ClubDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    ClubsDetailsContent(
        state = state,
        onBack = { navController.popBackStack() },
        onRefresh = viewModel::swipeToRefresh,
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(id = it.postId, publisherId = it.publisherId)
        },
        onClickSave = viewModel::onClickSave,
        onAddPost = { navController.navigateToPostCreation(state.clubId) },
        onClickMembers = { navController.navigateToMembers(clubId = it, ownerId = state.ownerId) },
        onJoinClub = viewModel::joinClubs,
        onUnJoinClubs = viewModel::unJoinClubs,
        onDeclineClub = viewModel::declineRequestOfClub,
        onRetry = viewModel::getData,
        onClickJoinRequestClub = {
            navController.navigateToClubRequests(clubId = state.clubId, ownerId = state.ownerId)
        },
        onClickEditClub = {
            navController.navigateToEditClub(
                clubId = state.clubId,
                clubName = state.name,
                clubDescription = state.description,
                clubPrivacy = state.membership
            )
        },
        onClickDeletePost = viewModel::onDeletePost
    )

    val color = MaterialTheme.colors.onBackground
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
private fun ClubsDetailsContent(
    state: ClubDetailsUiState,
    onBack: () -> Unit,
    onRefresh: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onAddPost: () -> Unit,
    onClickMembers: (Int) -> Unit,
    onJoinClub: () -> Unit,
    onUnJoinClubs: () -> Unit,
    onDeclineClub: () -> Unit,
    onRetry: () -> Unit,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit,
    onClickDeletePost: (PostUIState) -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else {
            ManualPager(
                onRefresh = onRefresh,
                isLoading = state.isPagerLoading,
                error = state.pagerError,
                isEndOfPager = state.isEndOfPager,
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {

                item {
                    ClubHeaderDetails(
                        state = state,
                        onBack = onBack,
                        onClickJoinRequestClub = onClickJoinRequestClub,
                        onClickEditClub = onClickEditClub
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
                                .nonRippleEffect { onClickMembers(state.clubId) }
                                .padding(horizontal = 16.dp))
                    }

                    items(state.posts) {
                        PostItem(
                            state = it,
                            isContentExpandable = true,
                            isClubPost = true,
                            showGroupName = false,
                            onClickLike = onClickLike,
                            onClickComment = onClickComment,
                            onClickSave = { onClickSave(it) },
                            onClickPostSetting = onClickDeletePost,
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
                                .nonRippleEffect { onClickMembers(state.clubId) }
                                .padding(horizontal = 16.dp))
                    }

                    // why this screen has 2 of below item ????!!!!!!
                    items(state.posts) {
                        PostItem(
                            state = it,
                            isContentExpandable = true,
                            isClubPost = true,
                            onClickLike = onClickLike,
                            onClickComment = onClickComment,
                            onClickSave = onClickSave,
                            onClickPostSetting = onClickDeletePost,
                            onClickProfile = {},
                            onOpenLinkClick = {},
                        )
                    }
                }
            }

        }
    }

    LaunchedEffect(key1 = state.pagerError) {
        if (state.pagerError.isNotEmpty()) {
            Toast.makeText(context, state.pagerError, Toast.LENGTH_LONG).show()
        }
    }
}
