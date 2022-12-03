package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.profile.HorizontalSpacer8
import com.devfalah.ui.profile.VerticalSpacer8
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.AlbumUIState

@Composable
fun AlbumSection(
    albums: List<AlbumUIState>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF6F6F7))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.albums),
                color = LightSecondaryBlackColor,
                fontFamily = PlusJakartaSans,
                fontSize = 14.sp
            )

            Text(
                text = stringResource(id = R.string.see_all),
                color = LightTernaryBlackColor,
                fontFamily = PlusJakartaSans,
                fontSize = 12.sp
            )

            HorizontalSpacer8()

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                Modifier.size(24.dp)
            )
        }

        VerticalSpacer8()

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            albums.take(3).forEach {
                Image(
                    painter = rememberAsyncImagePainter(model = it.albumCover),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
    }
}

