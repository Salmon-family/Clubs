package com.devfalah.ui.screen.clubsDetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.ManualPager
import com.devfalah.ui.composable.RoundButton
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubsDetail.composable.ClubHeaderDetails
import com.devfalah.ui.screen.clubsDetail.composable.ClubMembers
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState
import com.devfalah.viewmodels.clubDetails.ClubDetailsViewModel
import com.devfalah.viewmodels.clubDetails.MembersUIState
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun ClubsDetailsScreen(
    navController: NavController,
    viewModel: ClubDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    ClubsDetailsContent(
        state = state
    )
}

@Composable
private fun ClubsDetailsContent(
    state: ClubDetailsUiState
) {

    ManualPager(
        onRefresh = {},
        isLoading = state.isLoading,
        error = "false",
        isEndOfPager = true,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            ClubHeaderDetails(
               state = state
            )
        }

        item {
            RoundButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                userState = UserState(),
                text = "Join Club",
                textColor = WhiteColor,
                onButtonClick = {}
            )

        }

        item {
            ClubMembers(
                friends = listOf(
                    MembersUIState(
                        id = 0,
                        profilePictureUrl = "",
                        name = "amnah",
                        title = "amnah"
                    )
                ),
                modifier = Modifier
                    .nonRippleEffect { }
                    .padding(horizontal = 16.dp)
            )
        }

    }

}