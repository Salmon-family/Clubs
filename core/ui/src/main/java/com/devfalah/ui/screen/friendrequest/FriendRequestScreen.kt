package com.devfalah.ui.screen.friendrequest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleProfileImage
import com.devfalah.ui.spacer.*
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.friendRequest.FriendRequestUiState
import com.devfalah.viewmodels.friendRequest.FriendRequestViewModel
import com.devfalah.viewmodels.friendRequest.UserState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendRequestScreen(
    navController: NavController,
    friendRequestViewModel: FriendRequestViewModel = hiltViewModel()
) {
    val friendRequest by friendRequestViewModel.friendRequests.collectAsState()
    FriendRequests(
        friendRequestUiState = friendRequest,
        onAcceptButtonClick = friendRequestViewModel::acceptFriendRequest,
        onDeleteButtonClick = friendRequestViewModel::deniedFriendRequest
    )
}

@ExperimentalFoundationApi
@Composable
fun FriendRequests(
    friendRequestUiState: FriendRequestUiState,
    onAcceptButtonClick: (Int) -> Unit,
    onDeleteButtonClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = friendRequestUiState.friendRequests,
            key = { currentRequest -> currentRequest.userID }
        ) { userState ->
            FriendRequestItem(
                userState = userState,
                onAcceptButtonClick = onAcceptButtonClick,
                onDeleteButtonClick = onDeleteButtonClick,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}

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


@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    userState: UserState,
    onButtonClick: (Int) -> Unit,
    buttonColor: Color = LightPrimaryBrandColor,
    text: String,
    textColor: Color,
    roundCorner: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Button(
        onClick = { onButtonClick(userState.userID) },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(roundCorner.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
    }
}