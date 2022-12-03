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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.spacer.HeightSpacer
import com.devfalah.ui.spacer.WidthSpacer
import com.devfalah.ui.theme.GrayColor
import com.devfalah.ui.theme.PrimaryColor
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
        contentPadding = PaddingValues(10.dp),
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
            .height(72.dp)
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        CircleImage(userState.profileImage)
        WidthSpacer(16)
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeightSpacer(5)
            Text(
                text = userState.name,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.wrapContentSize()
            )
            HeightSpacer(14)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                FriendRequestAcceptButton(
                    userState,
                    onAcceptButtonClick = onAcceptButtonClick
                )
                WidthSpacer(width = 16)
                FriendRequestDeleteButton(
                    userState,
                    onDeleteButtonClick = onDeleteButtonClick
                )
            }
        }
    }

}

@Composable
@Preview
fun FriendRequestScreenPreview() {
}

@Composable
fun FriendRequestAcceptButton(
    userState: UserState,
    onAcceptButtonClick: (Int) -> Unit
) {
    Button(
        onClick = { onAcceptButtonClick(userState.userID) },
        modifier = Modifier
            .width(114.dp)
            .height(30.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
    ) {
        Text(
            text = stringResource(id = R.string.accept),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun FriendRequestDeleteButton(
    userState: UserState,
    onDeleteButtonClick: (Int) -> Unit
) {
    Button(
        onClick = { onDeleteButtonClick(userState.userID) },
        modifier = Modifier
            .width(114.dp)
            .height(30.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(GrayColor)
    ) {
        Text(
            text = stringResource(id = R.string.delete),
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.wrapContentSize()
        )
    }
}



