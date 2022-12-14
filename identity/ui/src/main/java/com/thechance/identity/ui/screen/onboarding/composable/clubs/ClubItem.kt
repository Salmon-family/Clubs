package com.thechance.identity.ui.screen.onboarding.composable.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.*
import com.thechance.identity.viewmodel.clubs.ClubUIState

@Composable
fun ClubItem(
    club: ClubUIState,
    tintColor: Color = Color.Black,
    isSelected: Boolean = false,
    onSelectionChanged: (Boolean) -> Unit,
    selectedColor: Color = LightPrimaryBrandColor
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = if (isSelected) selectedColor else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = LightBackground,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {onSelectionChanged(!isSelected)}
    ) {
        Icon(
            painter = painterResource(id = club.icon),
            contentDescription = null,
            tint = if(isSelected) selectedColor else tintColor,
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = club.name,
            style = Typography.CardTitle,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(bottom = 30.dp),
            textAlign = TextAlign.Center
        )
    }
}