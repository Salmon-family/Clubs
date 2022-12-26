package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer12
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.clubDetails.MembersUIState

@Composable
fun ClubMembers(
    friends: List<MembersUIState>,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.clubs_member),
                fontFamily = PlusJakartaSans,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        HeightSpacer8()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            friends.take(4).forEach {
                Members(
                    modifier = Modifier.padding(8.dp),
                    painter = rememberAsyncImagePainter(model = it.profilePictureUrl),
                    text = it.name,
                )
            }

            WidthSpacer12()

            if (friends.size > 4) {
                Members(
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    painter = painterResource(id = R.drawable.ic_more),
                    text = stringResource(R.string.more),
                    textColor = MaterialTheme.colors.onSurface
                )
            }
        }

    }
}

@Composable
private fun Members(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    textColor: Color = LightPrimaryBlackColor
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircleImage(
            painter = painter,
            size = 56
        )
        HeightSpacer8()
        Text(
            text = text,
            modifier = Modifier.sizeIn(maxWidth = 56.dp),
            color = textColor,
            fontFamily = PlusJakartaSans,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}
