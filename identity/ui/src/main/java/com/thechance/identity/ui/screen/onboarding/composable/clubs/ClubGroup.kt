package com.thechance.identity.ui.screen.onboardclubs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.model.Club
import com.thechance.identity.ui.model.getClubs

@Composable
fun ClubGroup(
    clubs: List<Club> = getClubs(),
    selectedClub: Club? = null,
    onSelectedChanged: (String) -> Unit = {}
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubGroup(){
    ClubGroup()
}