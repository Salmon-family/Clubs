package com.devfalah.ui.screen.friendrequest.friendcomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleProfileImage
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.RoundButton
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun FriendRequestItem(
    userState: UserState,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleProfileImage(
            painter = rememberAsyncImagePainter(model = userState.profileImage),
            size = 72
        )
        WidthSpacer16()
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = userState.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = LightPrimaryBlackColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            HeightSpacer8()
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                RoundButton(
                    modifier = Modifier.weight(1f),
                    userState = userState,
                    onButtonClick = onAcceptButtonClick,
                    buttonColor = LightPrimaryBrandColor,
                    text = stringResource(id = R.string.accept),
                    textColor = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                WidthSpacer16()
                RoundButton(
                    modifier = Modifier.weight(1f),
                    userState = userState,
                    onButtonClick = onDeleteButtonClick,
                    buttonColor = LightCardBackgroundColor,
                    text = stringResource(id = R.string.delete),
                    textColor = Color.Black
                )
            }
        }
    }
}