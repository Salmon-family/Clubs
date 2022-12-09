package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.HeightSpacer4
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

@Composable
fun ProfileDetailsSection(
    userDetails: UserDetailsUIState,
    modifier: Modifier = Modifier,
    onChangeProfileImage: () -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {

        val (imageCover, imageProfile) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(model = userDetails.coverUrl),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .constrainAs(imageCover) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )

        Image(
            painter = rememberAsyncImagePainter(model = userDetails.profilePicture),
            contentDescription = null,
            modifier = Modifier
                .clickable { onChangeProfileImage() }
                .constrainAs(imageProfile) {
                    top.linkTo(imageCover.bottom)
                    start.linkTo(imageCover.start)
                    end.linkTo(imageCover.end)
                    bottom.linkTo(imageCover.bottom)
                }
                .size(112.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape),
        )
    }

    HeightSpacer8()

    Text(
        text = userDetails.name,
        color = LightPrimaryBlackColor,
        fontFamily = PlusJakartaSans,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 18.sp
    )

    Text(
        text = "@${userDetails.userName}",
        color = LightTernaryBlackColor,
        fontFamily = PlusJakartaSans,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 14.sp
    )

    HeightSpacer8()

    Text(
        text = userDetails.title,
        color = LightPrimaryBrandColor,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp
    )

    HeightSpacer4()

    Text(
        text = userDetails.bio,
        color = LightPrimaryBlackColor,
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}