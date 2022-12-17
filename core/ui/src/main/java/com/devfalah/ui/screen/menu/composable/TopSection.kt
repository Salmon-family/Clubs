package com.devfalah.ui.screen.menu.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.viewmodels.menu.UserUiState

@Composable
fun TopSection(
    state: UserUiState,
    modifier: Modifier = Modifier,
    onClickProfile: () -> Unit,
    onClickSavedThreads: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileItem(
            painter = rememberAsyncImagePainter(
                model = state.profilePhotoUrl,
                placeholder = painterResource(id = R.drawable.ic_menu_avatar)
            ),
            onClickItem = onClickProfile,
        )

        HeightSpacer16()

        MenuItem(
            text = stringResource(R.string.saved_threads),
            painter = painterResource(R.drawable.ic_menu_saved),
            onClickItem = onClickSavedThreads
        )
    }
}

@Preview
@Composable
fun PreviewTopSection() {
    TopSection(
        state = UserUiState(),
        onClickProfile = {},
        onClickSavedThreads = {}
    )
}