package com.devfalah.ui.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.*
import com.devfalah.ui.image.navigateToImageScreen
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.viewmodels.home.HomeUIState
import com.devfalah.viewmodels.home.HomeViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.util.Constants.HOME_CLUB_ID
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()

    HomeContent(
        state = state,
        onClickLike = viewModel::onClickLike,
        onClickComment = {
            navController.navigateToPostDetails(id = it.postId, publisherId = it.publisherId)
        },
        onClickSave = viewModel::onClickSave,
        onCreatePost = { navController.navigateToPostCreation(HOME_CLUB_ID) },
        onRefresh = viewModel::getMorePosts,
        updateHome = viewModel::updateHome,
        onDeletePost = viewModel::onDeletePost,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) },
        onClickChat = { goToChat(context) },
        onRetry = viewModel::getData,
        onImageClick = { navigateToImageScreen(context, it) }
    )
    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
fun HomeContent(
    state: HomeUIState,
    onCreatePost: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onRefresh: () -> Unit,
    updateHome: () -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onDeletePost: (PostUIState) -> Unit,
    onClickChat: () -> Unit,
    onRetry: () -> Unit,
    onImageClick: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(
            title = stringResource(id = Screen.Home.title),
            showBackButton = false,
            actions = {
                IconButton(onClick = onClickChat) {
                    Icon(
                        modifier = Modifier.flipWithLanguage(),
                        imageVector = ImageVector.vectorResource(id = R.drawable.send),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        )
        Box {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
                onRefresh = updateHome,
                indicatorAlignment = Alignment.TopCenter,
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.primary,
                        shape = CircleShape,
                    )
                },
                content = {
                    ManualPager(
                        onRefresh = onRefresh,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        isLoading = state.isPagerLoading && !state.isLoading ,
                        error = state.pagerError,
                        isEndOfPager = state.isEndOfPager,
                    ) {
                        item {
                            PostCreatingSection(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                onCreatePost = onCreatePost,
                                isMyProfile = true
                            )
                        }

                        items(state.posts) {
                            if (state.error.isEmpty()) {
                                PostItem(
                                    state = it,
                                    isContentExpandable = true,
                                    isClubPost = false,
                                    showGroupName = false,
                                    onClickLike = onClickLike,
                                    onClickComment = onClickComment,
                                    onClickSave = onClickSave,
                                    onClickProfile = onClickProfile,
                                    onClickPostSetting = onDeletePost,
                                    onOpenLinkClick = onOpenLinkClick,
                                    onImageClick = onImageClick,
                                    )
                            }
                        }
                    }
                }
            )

            if (state.error.isNotBlank()) {
                ErrorItem(onClickRetry = onRetry)
            }
        }
    }
}


fun openBrowser(context: Context, url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    startActivity(context, openURL, null)
}

private fun goToChat(context: Context) {
    try {
        val intent = Intent(context, Class.forName("com.thechance.ui.ChatActivity"))
        startActivity(context, intent, Bundle())
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
}

