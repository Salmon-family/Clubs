package com.devfalah.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleProfileImage
import com.devfalah.ui.spacer.*
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.search.ClubStatus
import com.devfalah.viewmodels.search.ProfileStatus

@Composable
fun SearchScreen(navController: NavController) {}

@Composable
fun SearchPeopleItem(
    profileStatus: ProfileStatus,
    onStatusClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleProfileImage(
            painter = painterResource(id = R.drawable.test_profile_cover),
            size = 56
        )
        WidthSpacer16()
        Column(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            ProfileName("Noor mohammed")
            HeightSpacer4()
            ProfileAbstract("Android developer")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onStatusClick
            ) {
                Icon(
                    painter = profileStatus.checkProfileStatus(),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileName(
    fullName: String
) {
    Text(
        text = fullName,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = LightPrimaryBlackColor
    )
}

@Composable
fun ProfileAbstract(
    abstract: String
) {
    Text(
        text = abstract,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = LightPrimaryBrandColor
    )
}

@Preview
@Composable
fun SearchPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        SearchComponent(
            "",
            onTextChange = {},
            onSearchClicked = {}
        )
        HeightSpacer16()
        TitleAndSeeAll(
            title = "People",
            onSeeAllClick = {}
        )
        HeightSpacer16()
        SearchPeopleItem(
            onStatusClick = {},
            profileStatus = ProfileStatus.ALREADY_SENT
        )
        HeightSpacer16()
        TitleAndSeeAll(
            title = "Clubs",
            onSeeAllClick = {}
        )
        SearchClubsItem(
            clubsName = "Astronomy Club",
            membersCount = 10,
            postsCount = 100,
            clubStatus = ClubStatus.Joined,
            onClubButtonClick = {}
        )
    }
}

@Composable
fun TitleAndSeeAll(
    title: String,
    onSeeAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = LightPrimaryBlackColor,
            modifier = Modifier.wrapContentSize()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.see_all),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = TernaryBlackColor
            )
            WidthSpacer8()
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onSeeAllClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "SEE_ALL",
                    tint = LightSecondaryBlackColor
                )
            }
        }
    }

}

@Composable
fun SearchComponent(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked(text)
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = LightPrimaryBrandColor,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = LightCardBackgroundColor
        ),
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(100.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(R.string.search_for_a_clubs),
                fontSize = 14.sp,
                color = TernaryBlackColor,
                fontWeight = FontWeight.Medium
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_outline),
                contentDescription = "SEARCH_ICON",
                tint = LightQuaternaryBlackColor,
            )
        },
    )
}

@Composable
fun SearchClubsItem(
    clubsName: String,
    membersCount: Int,
    postsCount: Int,
    clubStatus: ClubStatus,
    onClubButtonClick: (ClubStatus) -> Unit
) {
    Card(
        modifier = Modifier
            .width(156.dp)
            .height(256.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = LightSecondaryBrandColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.test_profile_cover),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(156.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                HeightSpacer8()
                Text(
                    text = clubsName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightPrimaryBlackColor
                )
                Text(
                    text = "$membersCount member,$postsCount post",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = TernaryBlackColor
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ButtonClubItemStatus(clubStatus,onClubButtonClick)
                    HeightSpacer8()
                }
            }
        }
    }
}

@Composable
fun ProfileStatus.checkProfileStatus(): Painter {
    return when (this) {
        ProfileStatus.ALREADY_SENT -> painterResource(id = R.drawable.ic_sent_friend_request)
        ProfileStatus.FRIEND -> painterResource(id = R.drawable.ic_already_friend)
        ProfileStatus.SEND_REQUEST -> painterResource(id = R.drawable.ic_add_friend)
    }
}

@Composable
fun ButtonClubItemStatus(
    clubStatus: ClubStatus,
    onButtonClick: (ClubStatus) -> Unit,
) {
    when(clubStatus){
        ClubStatus.CanselRequest ->{ ButtonClubItem(clubStatus,onButtonClick) }
        ClubStatus.JoinClub ->{ ButtonClubItem(clubStatus, onButtonClick, WhiteColor) }
        ClubStatus.Joined ->{ ButtonClubItem(clubStatus,onButtonClick) }
    }
}

@Composable
fun ButtonClubItem(
    clubStatus: ClubStatus,
    onButtonClick: (ClubStatus) -> Unit,
    color: Color = TernaryWhiteColor
) {
    Button(
        onClick = { onButtonClick(clubStatus) },
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        elevation = ButtonDefaults.elevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = clubStatus.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightPrimaryBrandColor
            )
            ButtonClubIcon(clubStatus = clubStatus)
        }
    }
}

@Composable
private fun ButtonClubIcon(clubStatus: ClubStatus){
    if(clubStatus == ClubStatus.Joined) Icon(
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = null,
        tint = LightPrimaryBrandColor
    )
}