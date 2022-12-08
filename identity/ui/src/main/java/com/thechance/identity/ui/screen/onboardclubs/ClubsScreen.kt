package com.thechance.identity.ui.screen.onboardclubs

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.onboardclubs.composable.ClubsTitle
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor

@Composable
fun ClubsScreen(
    navController: NavController
){

}

@Composable
fun ClubsContent(
    onClickContinue: () -> Unit,
    onClickBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackButton(onClick = onClickBack)

        Spacer(modifier = Modifier.weight(1f))
        ClubsTitle(
            text1 = stringResource(id = R.string.your_label),
            color1 = LightPrimaryBlackColor,
            text2 = stringResource(id = R.string.app_name),
            color2 = LightPrimaryBrandColor
        )

        ClubGroup()
        
        Spacer(modifier = Modifier.weight(4.25f))
        AuthButton(
            onClick = onClickContinue,
            isEnabled = true,
            text = stringResource(id = R.string.continue_label),
            buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubsScreen(){
    ClubsContent(onClickContinue = {}) {
        
    }
}