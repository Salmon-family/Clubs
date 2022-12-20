package com.devfalah.ui.screen.accountSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devfalah.ui.theme.LightBackgroundColor

@Composable
fun AccountSettingsScreen(
    navController: NavController,
) {
    AccountSettingsContent()
}

@Composable
fun AccountSettingsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("This is Settings Screen")
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMenu() {
    AccountSettingsContent()
}