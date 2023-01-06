package com.thechance.ui.screens.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.thechance.ui.modifiers.nonRippleEffect
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.viewmodels.friends.uistate.FriendUiState

@Composable
fun FriendItem(
    modifier: Modifier = Modifier,
    state: FriendUiState,
    onClickNewFriend: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .nonRippleEffect { onClickNewFriend(state.id) }
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleImage(
            painter = rememberAsyncImagePainter(model = state.icon),
            size = 56
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = state.title,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = LightPrimaryBrandColor,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )
        }

    }
}