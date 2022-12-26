package com.devfalah.ui.screen.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.allSearchResultScreen.navigateToAllSearchResult
import com.devfalah.ui.screen.profile.navigateToProfile
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.util.Constants.SEARCH_CLUB
import com.devfalah.viewmodels.util.Constants.SEARCH_USER
import com.devfalah.viewmodels.search.SearchUIState
import com.devfalah.viewmodels.search.SearchViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val context = LocalContext.current

    SearchContent(
        state = state,
        onSearchValueChanged = viewModel::onSearchTextChange,
        onClubSelected = {
            Toast.makeText(
                context,
                "Should Navigate to Club Id = $it",
                Toast.LENGTH_LONG
            ).show()
        },
        OnUserClick = { navController.navigateToProfile(it) },
        onClickSeeAllClubs = {
            navController.navigateToAllSearchResult(
                title = "Clubs", keyword = state.keyword, searchType = SEARCH_CLUB
            )
        },
        onClickSeeAllPeople = {
            navController.navigateToAllSearchResult(
                title = "People", keyword = state.keyword, searchType = SEARCH_USER
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchContent(
    state: SearchUIState,
    onSearchValueChanged: (String) -> Unit,
    onClubSelected: (Int) -> Unit,
    OnUserClick: (Int) -> Unit,
    onClickSeeAllClubs: () -> Unit,
    onClickSeeAllPeople: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader(key = "keyword") {
            SearchTextField(
                text = state.keyword,
                onValueChanged = onSearchValueChanged,
                modifier = Modifier.background(MaterialTheme.colors.background)
            )
        }

        if (state.users.isNotEmpty()) {
            item("Users") {
                RowTitleWithSeeAll(
                    title = stringResource(id = R.string.people),
                    onclickSeeAll = onClickSeeAllPeople,
                    showSeeAll = state.showSeeAllClubs
                )
            }
        }

        items(
            items = state.users,
            key = { it.id }
        ) { user ->
            FriendItem(state = user, onOpenProfileClick = OnUserClick)
        }

        if (state.clubs.isNotEmpty()) {
            item("Clubs") {
                HeightSpacer24()

                RowTitleWithSeeAll(
                    title = stringResource(id = R.string.clubs),
                    onclickSeeAll = onClickSeeAllClubs,
                    showSeeAll = state.showSeeAllClubs
                )
            }
        }

        items(
            items = state.clubs,
            key = { "${it.id} ${it.title}" }
        ) { club ->
            ClubItem(state = club, onClubSelected = onClubSelected)
        }
    }
}



