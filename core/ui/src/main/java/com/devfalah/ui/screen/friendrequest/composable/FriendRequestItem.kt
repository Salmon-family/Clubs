package com.devfalah.ui.screen.friendrequest.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.HeightSpacer4
import com.devfalah.ui.composable.UserIconButton
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun FriendRequestItem(
    userState: UserState,
    onAcceptButtonClick: (Int, String) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onClickOpenProfile: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleImage(
            modifier = Modifier.nonRippleEffect { onClickOpenProfile(userState.userID) },
            painter = rememberAsyncImagePainter(model = userState.profileImage),
            size = 56
        )

        WidthSpacer16()

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userState.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            HeightSpacer4()
            Text(
                text = userState.title,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = LightPrimaryBrandColor,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )
        }

        WidthSpacer16()

        UserIconButton(
            userState = userState,
            onButtonClick = { onDeleteButtonClick(userState.userID) },
            iconsSize = 20.dp,
            painter = painterResource(id = R.drawable.ic_x)
        )

        WidthSpacer16()

        UserIconButton(
            userState = userState,
            onButtonClick = { onAcceptButtonClick(userState.userID, userState.token) },
            iconsSize = 32.dp,
            painter = painterResource(id = R.drawable.ic_accept_filled)
        )

    }
}

@Preview(showSystemUi = false)
@Composable
fun PreviewFoo() {
    FriendRequestItem(
        userState = UserState(1, "Mustafa Ahmed", "Android Developer"),
        onAcceptButtonClick = { _, _ -> },
        onDeleteButtonClick = {},
        onClickOpenProfile = {}
    )
}