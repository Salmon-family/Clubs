package com.thechance.identity.ui.screen.onboarding.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.onboarding.composable.clubs.ClubItem
import com.thechance.identity.ui.screen.onboarding.composable.clubs.ClubsTitle
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.viewmodel.clubs.ClubUIState
import com.thechance.identity.viewmodel.clubs.ClubsUIState
import com.thechance.identity.viewmodel.clubs.ClubsViewModel

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel: ClubsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ClubsContent(
        state = state,
        onSelectedChanged = viewModel::onChangeSelectedClub,
        onClickContinue = viewModel::joinClubs
    )
}

@Composable
fun ClubsContent(
    state: ClubsUIState,
    onSelectedChanged: (ClubUIState) -> Unit,
    onClickContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(LightCardColor)
    ) {
        BackButton(onClick = {})
        SpacerVertical24()

        ClubsTitle(
            text1 = stringResource(id = R.string.your_label),
            color1 = LightPrimaryBlackColor,
            text2 = stringResource(id = R.string.app_name),
            color2 = LightPrimaryBrandColor
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.clubs) { club ->
                ClubItem(
                    club = club,
                    isSelected = state.selectedClubs.contains(club),
                    onSelectionChanged = onSelectedChanged
                )
            }
        }

        SpacerVertical24()
        AuthButton(
            onClick = onClickContinue,
            isEnabled = state.selectedClubs.isNotEmpty(),
            text = stringResource(id = R.string.continue_label),
            buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}