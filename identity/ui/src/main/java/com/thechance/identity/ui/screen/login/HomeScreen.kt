package com.thechance.identity.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.composable.BackButtonComposable
import com.thechance.identity.ui.composable.TextComposable
import com.thechance.identity.ui.spacer.SpacerVertical12
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun HomeScreen(
    navController: NavController,
){
    HomeContent(
        onClickBack = {navController.navigateUp()}
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
        SpacerVertical12()
        BackButtonComposable(onClick = onClickBack)

        TextComposable(
            text = "Done",
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}

