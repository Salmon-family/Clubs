package com.devfalah.ui.screen.clubs

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
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
import com.devfalah.ui.screen.clubs.composable.MyClubCard
import com.devfalah.ui.screen.clubs.composable.SpecialClubItem
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.Typography
import com.devfalah.viewmodels.myClubs.ClubsState
import com.devfalah.viewmodels.myClubs.MyClubsUiState
import com.devfalah.viewmodels.myClubs.MyClubsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel: MyClubsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ClubsContent(state = state, onClubClick = viewModel::onClubClicked)


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClubsContent(
    state: MyClubsUiState,
    onClubClick: (ClubsState) -> Unit
) {
    var animationState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(LightCardBackgroundColor)
    ) {
        Button(
            onClick = { animationState = !animationState },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "click me")
        }

        AnimatedContent(targetState = animationState,
            transitionSpec = {
                slideInHorizontally(tween(300)) with fadeOut(tween(300))
            }

        ) {
            when (it) {
                true -> SpecialClubsScreen(state = state, onClubClick = onClubClick)
                false -> MyClubsScreen(state = state, onClubClick = onClubClick)
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyClubsScreen(
    state: MyClubsUiState,
    onClubClick: (ClubsState) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightCardBackgroundColor),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HorizontalPager(count = 2) {

            }

        }



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
                    style = Typography.subtitle1,
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
                items(state.myClubs) {
                    SpecialClubItem(R.drawable.art_icon, "art")
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.my_clubs),
                style = Typography.subtitle1,
                color = LightPrimaryBlackColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(state.myClubs) {
            MyClubCard(
                myClub = it,
                modifier = Modifier.padding(horizontal = 16.dp),
                onClubClick = onClubClick
            )
        }
    }
}

@Composable
fun SpecialClubsScreen(
    state: MyClubsUiState,
    onClubClick: (ClubsState) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(LightCardBackgroundColor),
    ) {

        items(9) {
            SpecialClubItem(iconId = R.drawable.art_icon, clubTitle = "art")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ClubsContent(MyClubsUiState()) {}
}
