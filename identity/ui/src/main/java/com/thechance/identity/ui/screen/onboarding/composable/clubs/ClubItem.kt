package com.thechance.identity.ui.screen.onboarding.composable.clubs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.CardTitle
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.clubs.ClubUIState

@Composable
fun ClubItem(
    club: ClubUIState,
    tintColor: Color = MaterialTheme.colors.primaryVariant,
    isSelected: Boolean = false,
    onSelectionChanged: (ClubUIState) -> Unit = {},
    selectedColor: Color = LightPrimaryBrandColor
) {
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
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(club)
                }
            )
    ) {
        Icon(
            painter = if (isSelected) painterResource(id = club.fillLineIcon) else painterResource(id = club.outLineIcon),
            contentDescription = null,
            tint = if (isSelected) selectedColor else tintColor,
            modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp)
        )

        Spacer(Modifier.height(8.dp))
        Text(
            text = club.name,
            style = Typography.CardTitle,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(bottom = 30.dp),
            textAlign = TextAlign.Center
        )
    }
}