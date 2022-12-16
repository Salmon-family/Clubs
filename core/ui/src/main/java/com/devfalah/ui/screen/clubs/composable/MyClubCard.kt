package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.Typography
import com.devfalah.viewmodels.myClubs.MyClubsState

@Composable
fun MyClubCard(
    myClub: MyClubsState,
    onClubClick: (MyClubsState) -> Unit
    ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { onClubClick(myClub) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,


    ) {

        Image(painter = rememberAsyncImagePainter(model = myClub.coverUrl),
            contentDescription = "my club cover",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )

        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp)) {
            Text(text = myClub.title,
                style = Typography.subtitle2,
                color = LightPrimaryBlackColor
            )

            Text(text = myClub.description,
                style = Typography.caption,
                color = LightSecondaryBlackColor
            )

        }

        Icon(painter = painterResource(id = R.drawable.star),
            contentDescription = null,
        modifier = Modifier.size(24.dp),
        tint = LightPrimaryBrandColor)

    }
}

@Preview
@Composable
private fun Preview() {
    MyClubCard(myClub = MyClubsState(
        "Cooking Club",
        "this club is for cooking",
        "",
        "1"
    ),
        onClubClick = {}
    )
}