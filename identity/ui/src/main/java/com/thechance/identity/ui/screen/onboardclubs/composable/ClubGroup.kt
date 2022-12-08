package com.thechance.identity.ui.screen.onboardclubs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(clubs){
            PreviewClubItem()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubGroup(){
    ClubGroup()
}