package com.thechance.identity.ui.screen.onboarding.clubs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.*
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.onboardclubs.ClubGroup
import com.thechance.identity.ui.screen.onboardclubs.PreviewClubItem
import com.thechance.identity.ui.screen.onboarding.composable.clubs.ClubsTitle
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor

@Composable
fun ClubsScreen(
    navController: NavController
){
    ClubsContent(
        onClickBack = {},
        onClickContinue = {}
    )
}

@Composable
fun ClubsContent(
    onClickContinue: () -> Unit,
    onClickBack: () -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            BackButton(onClick = onClickBack)
        }

        item {
            SpacerVertical24()
        }

        item {
            ClubsTitle(
                text1 = stringResource(id = R.string.your_label),
                color1 = LightPrimaryBlackColor,
                text2 = stringResource(id = R.string.app_name),
                color2 = LightPrimaryBrandColor
            )
        }

        item {
            FlowRow(
                modifier = Modifier.fillMaxSize().padding(vertical = 24.dp),
                mainAxisSpacing = 12.dp,
                crossAxisSpacing = 12.dp,
                mainAxisAlignment = MainAxisAlignment.Center,
                crossAxisAlignment = FlowCrossAxisAlignment.Center,
                mainAxisSize = com.google.accompanist.flowlayout.SizeMode.Expand
            ) {
                for (i in 0..1) {
                    PreviewClubItem()
                }
            }
        }

        item {
            SpacerVertical24()
        }

        item {
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
}
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            //.verticalScroll(rememberScrollState())
//    ) {
//        BackButton(onClick = onClickBack)
//
//        Spacer(modifier = Modifier.weight(1f))
//        ClubsTitle(
//            text1 = stringResource(id = R.string.your_label),
//            color1 = LightPrimaryBlackColor,
//            text2 = stringResource(id = R.string.app_name),
//            color2 = LightPrimaryBrandColor
//        )
//
//        ClubGroup()
////        FlowRow(
////            mainAxisSize = SizeMode.Expand,
////            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
////        ) {
////            for(i in 0..8){
////                PreviewClubItem()
////            }
////        }
//
//        Spacer(modifier = Modifier.weight(4.25f))
//        AuthButton(
//            onClick = onClickContinue,
//            isEnabled = true,
//            text = stringResource(id = R.string.continue_label),
//            buttonModifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 8.dp)
//        )
//    }


@Preview(showBackground = true)
@Composable
fun PreviewClubsScreen(){
    ClubsContent(onClickContinue = {}) {
        
    }
}