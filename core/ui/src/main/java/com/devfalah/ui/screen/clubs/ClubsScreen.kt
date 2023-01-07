package com.devfalah.ui.screen.clubs

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.clubCreation.CLUB_CREATION_ROUTE
import com.devfalah.ui.screen.clubs.composable.EmptyClubsItem
import com.devfalah.ui.screen.clubs.composable.SpecialClubItem
import com.devfalah.ui.screen.clubsDetail.navigateToClubDetails
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.util.observeAsState
import com.devfalah.viewmodels.myClubs.MyClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel: MyClubsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
    LaunchedEffect(key1 = lifecycleState.value) {
        if (lifecycleState.value == Lifecycle.Event.ON_RESUME) {
            viewModel.getData()
        }
    }
    ClubsContent(
        state = state,
        onClickCreateClub = { navController.navigate(CLUB_CREATION_ROUTE) },
        onClubClick = { navController.navigateToClubDetails(it) },
        onRetry = viewModel::getData
    )

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClubsContent(
    state: MyClubsUiState,
    onClickCreateClub: () -> Unit,
    onClubClick: (Int) -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.clubs),
                showBackButton = false,
                actions = {
                    IconButton(onClick = onClickCreateClub) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.create_club),
                            contentDescription = "create club icon",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->

        var animationState by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .background(MaterialTheme.colors.background)
        ) {
            SegmentControls(
                items = listOf(
                    stringResource(id = R.string.my_clubs),
                    stringResource(R.string.special_clubs)
                ),
                onItemSelection = {
                    animationState = it
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            HeightSpacer8()

            AnimatedContent(
                targetState = animationState,
                transitionSpec = {
                    slideInHorizontally(tween(300)) with fadeOut(tween(300))
                }

            ) {
                when (it) {
                    0 -> MyClubsScreen(state = state, onClubClick = onClubClick, onRetry = onRetry)
                    1 -> SpecialClubsScreen(state = state, onClubClick = onClubClick)
                }
            }
        }
    }
}

@Composable
fun MyClubsScreen(
    state: MyClubsUiState,
    onClubClick: (Int) -> Unit,
    onRetry: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.isLoading) {
            Loading()
        } else if (state.myClubs.isEmpty()) {
            EmptyClubsItem()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                contentPadding = if (state.mySpecialClubs.isNotEmpty())
                    PaddingValues(vertical = 16.dp) else PaddingValues(vertical = 0.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (state.mySpecialClubs.isNotEmpty()) {
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
                                color = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(state.mySpecialClubs) { club ->
                            SpecialClubItem(
                                state = club,
                                iconId = club.image,
                                clubTitle = club.name,
                                modifier = Modifier.size(98.dp),
                                onClick = { onClubClick(club.id) }
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = stringResource(R.string.my_clubs),
                        style = AppTypography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                items(state.myClubs) {
                    ClubItem(
                        state = it,
                        onClubSelected = onClubClick,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialClubsScreen(
    state: MyClubsUiState,
    onClubClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        items(state.specialClubs) { club ->
            SpecialClubItem(
                state = club,
                iconId = club.image,
                clubTitle = club.name,
                modifier = Modifier.aspectRatio(1f),
                onClick = onClubClick
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
}
