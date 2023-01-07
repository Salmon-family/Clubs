package com.thechance.identity.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.screen.onboarding.pager.ON_BOARDING_PAGER_ROUTE
import com.thechance.identity.ui.screen.signup.composable.BackPressHandler
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun HomeScreen(
    navController: NavController,
) {

    fun onBack() = navController.popBackStack(route = ON_BOARDING_PAGER_ROUTE, inclusive = false)
    BackPressHandler(onBackPressed = { onBack() })

    HomeContent(
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
private fun HomeContent(
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Done",
            style = Typography.h1,
            color = LightPrimaryBrandColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}

