package com.devfalah.ui.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.PostItem
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.screen.postCreation.CREATE_POST_SCREEN
import com.devfalah.ui.screen.postCreation.navigateToPostCreation
import com.devfalah.ui.screen.profile.composable.PostCreatingSection
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.home.HomeUIState
import com.devfalah.viewmodels.home.HomeViewModel
import com.devfalah.viewmodels.userProfile.PostUIState
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()
    HomeContent(
        navController = navController,
        state = state,
        onClickLike = viewModel::onClickLike,
        // should navigate to post screen details.
        onClickComment = { navController.navigate(CREATE_POST_SCREEN) },
        onClickSave = viewModel::onClickSave,
        onCreatePost = { navController.navigateToPostCreation() },
        onRefresh = viewModel::swipeToRefresh,
        onClickProfile = { navController.navigateToProfile(it) },
        onOpenLinkClick = { openBrowser(context, it) }
    )
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
}

@Composable
fun HomeContent(
    navController: NavController,
    state: HomeUIState,
    onCreatePost: () -> Unit,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onRefresh: (Int) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit
) {
    Column {

        AppBar(title = Screen.Home.title, navHostController = navController)

        ManualPager(
            onRefresh = onRefresh,
            contentPadding = PaddingValues(vertical = 16.dp),
            isLoading = state.isPagerLoading,
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
                PostItem(
                    state = it,
                    isContentExpandable = true,
                    isMyPost = it.publisherId == state.id,
                    onClickLike = { onClickLike(it) },
                    onClickComment = { onClickComment(it) },
                    onClickSave = { onClickSave(it) },
                    onClickProfile = onClickProfile,
                    onClickPostSetting = { },
                    onOpenLinkClick = onOpenLinkClick
                )
            }
        }
    }

}

fun openBrowser(context: Context, url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    startActivity(context, openURL, null)
}