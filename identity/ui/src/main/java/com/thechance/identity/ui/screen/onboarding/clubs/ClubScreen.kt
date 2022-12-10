package com.thechance.identity.ui.screen.onboardclubs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.model.Club
import com.thechance.identity.ui.model.getClubs
import com.thechance.identity.ui.screen.onboarding.composable.clubs.ClubsTitle
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor

@Composable
fun ClubScreen(
    clubs: List<Club> = getClubs(),
    selectedClub: Club? = null,
    onSelectedChanged: (String) -> Unit = {}
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(115.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.background(LightCardColor)
    ){
        item(
            span = {
                GridItemSpan(3)
            }
        ){
            BackButton(onClick = {})
            SpacerVertical24()
        }

        item(
            span = {
                GridItemSpan(3)
            }
        ) {
            ClubsTitle(
                text1 = stringResource(id = R.string.your_label),
                color1 = LightPrimaryBlackColor,
                text2 = stringResource(id = R.string.app_name),
                color2 = LightPrimaryBrandColor
            )
        }

        items(clubs){
            val state = remember {
                mutableStateOf(false)
            }

            ClubItem(
                iconId = R.drawable.ic_coding,
                tintColor = Color.Black,
                isSelected = state.value,
                text = "Coding",
                onChecked = {
                    state.value = it
                }
            )
            SpacerVertical24()
       }

        item(
            span = {
                GridItemSpan(3)
            }
        ) {
            AuthButton(
                onClick = {},
                isEnabled = true,
                text = stringResource(id = R.string.continue_label),
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubScreen(){
    ClubScreen()
}