package com.devfalah.ui.screen.clubs

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.composable.ClubItem
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.SegmentControls
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubCreation.CLUB_CREATION_ROUTE
import com.devfalah.ui.screen.clubs.composable.SpecialClubItem
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.myClubs.MyClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsViewModel

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel: MyClubsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    ClubsContent(navController = navController,
        state = state,
        onClubClick = viewModel::onClubClicked
    )


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClubsContent(
    navController: NavController,
    state: MyClubsUiState,
    onClubClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.clubs),
                navHostController = navController,
                actions = {
                    Icon(painter = painterResource(id = R.drawable.create_club),
                        contentDescription = "create club icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .nonRippleEffect { navController.navigate(CLUB_CREATION_ROUTE) }
                    )

                })
        }
    ) { scaffoldPadding ->

        var animationState by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .background(LightBackgroundColor)
        ) {

            SegmentControls(items = listOf(
                stringResource(id = R.string.my_clubs),
                stringResource(R.string.special_clubs)
            ),
                onItemSelection = {
                    animationState = it
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            HeightSpacer8()

            AnimatedContent(targetState = animationState,
                transitionSpec = {
                    slideInHorizontally(tween(300)) with fadeOut(tween(300))
                }

            ) {
                when (it) {
                    0 -> MyClubsScreen(state = state, onClubClick = onClubClick)
                    1 -> SpecialClubsScreen(state = state, onClubClick = onClubClick)
                }
            }
        }
    }

}

@Composable
fun MyClubsScreen(
    state: MyClubsUiState,
    onClubClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackgroundColor),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = LightPrimaryBrandColor
                )

                Text(
                    text = stringResource(R.string.my_special_clubs),
                    modifier = Modifier.padding(start = 8.dp),
                    style = AppTypography.subtitle1,
                    color = LightPrimaryBlackColor
                )
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(9) {
                    SpecialClubItem(R.drawable.art_icon, "art")
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.my_clubs),
                style = AppTypography.subtitle1,
                color = LightPrimaryBlackColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(state.myClubs) {
            ClubItem(state = it,
                onClubSelected = onClubClick,
            modifier = Modifier
                .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun SpecialClubsScreen(
    state: MyClubsUiState,
    onClubClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackgroundColor),
    ) {

        items(9) {
            SpecialClubItem(iconId = R.drawable.art_icon, clubTitle = "art")
        }
    }
}

@Preview
@Composable
private fun Preview() {
}
