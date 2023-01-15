package com.devfalah.ui.screen.menu.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8

@Composable
fun AccountSection(
    modifier: Modifier = Modifier,
    onClickAccountSettings: () -> Unit,
    onClickFriendsRequests: () -> Unit,
) {
    MenuSection(
        modifier = modifier,
        sectionTitle = stringResource(R.string.account_section),
    ) {
        MenuItem(
            text = stringResource(R.string.change_password),
            painter = painterResource(R.drawable.ic_menu_settings),
            onClickItem = onClickAccountSettings
        )

        HeightSpacer8()

        MenuItem(
            text = stringResource(R.string.friends_requests),
            painter = painterResource(R.drawable.ic_menu_friend_request),
            onClickItem = onClickFriendsRequests
        )

        HeightSpacer8()
    }
}

@Preview
@Composable
fun PreviewAccountSection() {
    AccountSection(
        onClickAccountSettings = {},
        onClickFriendsRequests = {}
    )
}