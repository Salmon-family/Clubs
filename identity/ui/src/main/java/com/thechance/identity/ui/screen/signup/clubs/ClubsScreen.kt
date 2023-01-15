package com.thechance.identity.ui.screen.signup.clubs

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.composable.Error
import com.thechance.identity.ui.composable.Loading
import com.thechance.identity.ui.screen.activation.navigateToAccountActivation
import com.thechance.identity.ui.screen.onboarding.pager.ON_BOARDING_PAGER_ROUTE
import com.thechance.identity.ui.screen.signup.composable.BackPressHandler
import com.thechance.identity.ui.screen.signup.composable.clubs.ClubItem
import com.thechance.identity.ui.screen.signup.composable.clubs.ClubsTitle
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.viewmodel.clubs.ClubUIState
import com.thechance.identity.viewmodel.clubs.ClubsUIState
import com.thechance.identity.viewmodel.clubs.ClubsViewModel
import com.thechance.identity.viewmodel.utils.ErrorMessageType

@Composable
fun ClubsScreen(
    navController: NavController,
    viewModel: ClubsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    fun onBack() = navController.popBackStack(route = ON_BOARDING_PAGER_ROUTE, inclusive = false)
    BackPressHandler(onBackPressed = { onBack() })

    ClubsContent(
        state = state,
        onSelectedChanged = viewModel::onChangeSelectedClub,
        onClickBack = { navController.popBackStack(route = ON_BOARDING_PAGER_ROUTE, inclusive = false) },
        onClickContinue = viewModel::onJoin,
    )

    LaunchedEffect(key1 = state.isLoading) {
        if (state.isSuccess) {
            navController.navigateToAccountActivation()
            Toast.makeText(context, R.string.success_message, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun ClubsContent(
    state: ClubsUIState,
    onClickBack: () -> Unit,
    onSelectedChanged: (ClubUIState) -> Unit,
    onClickContinue: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BackButton(onClick = onClickBack)
            SpacerVertical24()

            ClubsTitle(
                text1 = stringResource(id = R.string.your_label),
                color1 = MaterialTheme.colors.primaryVariant,
                text2 = stringResource(id = R.string.app_name),
                color2 = LightPrimaryBrandColor
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(top = 24.dp, bottom = 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f, fill = false)
            ) {
                items(state.clubs) { club ->
                    ClubItem(
                        club = club,
                        isSelected = state.selectedClubs.contains(club),
                        onSelectionChanged = onSelectedChanged
                    )
                }
            }

            if (state.errorType != ErrorMessageType.NO_ERROR) {
                Error(
                    errorMessage = stringResource(id = R.string.unknown_error_message),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            AuthButton(
                onClick = onClickContinue,
                isEnabled = state.selectedClubs.size in 1..3,
                text = stringResource(id = R.string.continue_label),
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        if (state.isLoading) {
            Loading()
        }
    }


}