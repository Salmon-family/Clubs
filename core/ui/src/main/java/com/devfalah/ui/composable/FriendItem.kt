package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.friends.FriendUIState

@Composable
fun FriendItem(
    modifier: Modifier = Modifier,
    state: FriendUIState,
    onOpenProfileClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .nonRippleEffect { onOpenProfileClick(state.id) }
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LightCardBackgroundColor)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleImage(
            painter = rememberAsyncImagePainter(model = state.profilePictureUrl),
            size = 56
        )

        WidthSpacer16()

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = LightPrimaryBlackColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            HeightSpacer4()
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

@Preview(showSystemUi = false)
@Composable
fun PreviewFriendItem() {
    FriendItem(
        state = FriendUIState(
            1, "", "Test Test", " Android Developer"
        ),
        onOpenProfileClick = {},
    )
}