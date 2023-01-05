package com.devfalah.ui.screen.clubsDetail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
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
import com.devfalah.ui.screen.clubsDetail.composable.*
import com.devfalah.ui.screen.editClub.navigateToEditClub
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState
import com.devfalah.viewmodels.clubDetails.ClubDetailsViewModel
import com.devfalah.viewmodels.clubDetails.isPostVisible
import com.devfalah.viewmodels.userProfile.PostUIState
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
        onAddPost = { navController.navigateToPostCreation(state.detailsUiState.clubId) },
        onClickMembers = {
            navController.navigateToMembers(
                clubId = it,
                ownerId = state.detailsUiState.ownerId
            )
        },
        onJoinClub = viewModel::joinClubs,
        onUnJoinClubs = viewModel::unJoinClubs,
        onRetry = viewModel::getDetailsOfClubs,
        onDeletePost = viewModel::onDeletePost,
        onClickJoinRequestClub = {
            navController.navigateToClubRequests(
                clubId = state.detailsUiState.clubId,
                ownerId = state.detailsUiState.ownerId
            )
        },
        onClickEditClub = {
            navController.navigateToEditClub(
                clubId = state.detailsUiState.clubId,
                clubName = state.detailsUiState.name,
                clubDescription = state.detailsUiState.description,
                clubPrivacy = state.detailsUiState.isClubPublic
            )
        }
    )

    val color = MaterialTheme.colors.onBackground
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    onRetry: () -> Unit,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit,
    onDeletePost: (PostUIState) -> Unit,
) {

    val context = LocalContext.current
    var popupController by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    ClubHeaderDetails(
                        state = state,
                        onBack = onBack,
                        onClickJoinRequestClub = onClickJoinRequestClub,
                        onClickEditClub = onClickEditClub,
                    )
                }) {
                ClubDetailsContent(
                    state = state,
                    onRefresh = onRefresh,
                    onClickLike = onClickLike,
                    onClickComment = onClickComment,
                    onClickSave = onClickSave,
                    onAddPost = onAddPost,
                    onClickMembers = onClickMembers,
                    onJoinClub = onJoinClub,
                    onDeletePost = onDeletePost
                )
            }
        }
    }


    if (popupController) {
        LeaveClubDialog(
            onDeclineClub = onUnJoinClubs,
            onPopupDismiss = { popupController = false }
        )

    }

    LaunchedEffect(key1 = state.pagerError) {
        if (state.pagerError.isNotEmpty()) {
            Toast.makeText(context, state.pagerError, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun ClubDetailsContent(
    state: ClubDetailsUiState,
    onRefresh: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onAddPost: () -> Unit,
    onClickMembers: (Int) -> Unit,
    onJoinClub: () -> Unit,
    onDeletePost: (PostUIState) -> Unit,
) {
    var popupController by remember { mutableStateOf(false) }

    ManualPager(
        modifier = Modifier
            .layoutId("lazyColumn"),
        onRefresh = onRefresh,
        isLoading = state.isPagerLoading,
        error = state.pagerError,
        isEndOfPager = state.isEndOfPager,
        contentPadding = PaddingValues(bottom = 16.dp),
        footerVisibility = state.isPostVisible()
    ) {

        item {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                ClubStats(state = state)
            }
        }

        if (!state.detailsUiState.isOwner) {
            item {

                if (state.isMember) {
                    OutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        onClick = {
                            popupController = true
                        }
                    )
                } else {
                    RoundButton(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(
                            id = if (state.requestExists) {
                                R.string.request_to_join
                            } else {
                                R.string.join_club
                            }
                        ),
                        onButtonClick = if (state.requestExists) {
                            { popupController = true }
                        } else {
                            onJoinClub
                        },
                    )
                }
            }
        }

        if (state.isMember) {
            item {
                PostCreatingSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onCreatePost = onAddPost,
                    isMyProfile = true
                )
            }
        }

        if (state.isPostVisible()) {
            item {
                ClubMembers(friends = state.members,
                    modifier = Modifier
                        .nonRippleEffect { onClickMembers(state.detailsUiState.clubId) }
                        .padding(horizontal = 16.dp))
            }
        }

        if (!state.detailsUiState.isClubPublic && !state.isMember) {
            item {
                PrivateClubsBox(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            }
        }

        if (state.isPostVisible()) {
            items(state.posts) {
                PostItem(
                    state = it,
                    isContentExpandable = true,
                    isClubPost = true,
                    onClickLike = onClickLike,
                    onClickComment = onClickComment,
                    onClickSave = onClickSave,
                    onClickPostSetting = onDeletePost,
                    onClickProfile = {},
                    onOpenLinkClick = {},
                )
            }
        }
    }
}
