package com.devfalah.ui.screen.clubsDetail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.image.navigateToImageScreen
import com.devfalah.ui.screen.clubMembers.navigateToMembers
import com.devfalah.ui.screen.clubRequests.navigateToClubRequests
import com.devfalah.ui.screen.clubsDetail.composable.*
import com.devfalah.ui.screen.editClub.navigateToEditClub
import com.devfalah.ui.screen.home.openBrowser
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.FriendsSection
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.util.observeAsState
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
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
    LaunchedEffect(key1 = lifecycleState.value) {
        if (lifecycleState.value == Lifecycle.Event.ON_RESUME) {
            viewModel.refreshClub()
        }
    }

    ClubsDetailsContent(
        state = state,
        onBack = { navController.popBackStack() },
        onRefresh = viewModel::getClubThreads,
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(id = it.postId)
        },
        onClickSave = viewModel::onClickSave,
        onAddPost = { navController.navigateToPostCreation(state.detailsUiState.clubId) },
        onClickMembers = {
            navController.navigateToMembers(
                clubId = state.detailsUiState.clubId,
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
        },
        onClickMember = { navController.navigateToProfile(it) },
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) },
        onImageClick = { navigateToImageScreen(context, it) }
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
    onClickMembers: () -> Unit,
    onClickMember: (Int) -> Unit,
    onJoinClub: () -> Unit,
    onUnJoinClubs: () -> Unit,
    onRetry: () -> Unit,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit,
    onDeletePost: (PostUIState) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
) {

    val context = LocalContext.current
    var popupController by remember { mutableStateOf(false) }
    val lazyScrollState = rememberLazyListState()


    Box {
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else {
            Scaffold(
                backgroundColor = MaterialTheme.colors.onBackground,
                topBar = {
                    ClubDetailsAppBar(
                        state = state,
                        lazyScrollState = lazyScrollState,
                        onBack = onBack,
                        onClickJoinRequestClub = onClickJoinRequestClub,
                        onClickEditClub = onClickEditClub
                    )
                }
            ) { paddingValues ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.onBackground)
                ) {
                    ManualPager(
                        modifier = Modifier
                            .padding(paddingValues),
                        onRefresh = onRefresh,
                        isLoading = state.isPagerLoading,
                        error = state.pagerError,
                        isEndOfPager = state.isEndOfPager,
                        scrollState = lazyScrollState,
                        contentPadding = PaddingValues(bottom = 16.dp),
                        footerVisibility = state.isPostVisible(),
                    ) {
                        item {
                            ClubHeaderDetails(state)
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
                                FriendsSection(
                                    friends = state.members,
                                    onClickMoreFriends = onClickMembers,
                                    onClickFriend = onClickMember,
                                    totalFriends = -1
                                )
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
                                    isClubPost = true,
                                    onClickLike = onClickLike,
                                    onClickComment = onClickComment,
                                    onClickSave = onClickSave,
                                    onClickPostSetting = onDeletePost,
                                    onClickProfile = onClickProfile,
                                    onOpenLinkClick = onOpenLinkClick,
                                    onImageClick = onImageClick,
                                )
                            }
                        }
                    }
                }
            }
        }

        if (popupController) {
            LeaveDialog(
                title = stringResource(id = R.string.leave_club_title),
                message = stringResource(id = R.string.are_you_sure),
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
}
