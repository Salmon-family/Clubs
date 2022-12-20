package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

@Composable
fun ProfileDetailsSection(
    userDetails: UserDetailsUIState,
    modifier: Modifier = Modifier,
    onChangeProfileImage: () -> Unit,
    onSendRequestClick: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(LightPrimaryBrandColor)
    ) {

        val (imageCover, imageProfile, textTitle, textName, bioLayer) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .constrainAs(imageCover) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = userDetails.profilePicture,
                    error = painterResource(id = R.drawable.test_image)
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightPrimaryBrandTransparentColor)
            )
        }

        Text(
            text = userDetails.title,
            modifier = Modifier
                .constrainAs(textTitle) {
                    top.linkTo(imageCover.top)
                    start.linkTo(imageCover.start)
                }
                .padding(start = 24.dp, top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 1
        )
        Text(
            text = userDetails.name,
            modifier = Modifier
                .constrainAs(textName) {
                    top.linkTo(textTitle.bottom)
                    start.linkTo(textTitle.start)
                }
                .padding(start = 24.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 2
        )

        Box(
            modifier = Modifier
                .constrainAs(imageProfile) {
                    top.linkTo(imageCover.bottom)
                    start.linkTo(imageCover.start)
                    end.linkTo(imageCover.end)
                    bottom.linkTo(imageCover.bottom)
                }
                .fillMaxWidth()
                .height(170.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(120.dp)
                    .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                    .background(LightBackgroundColor),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = userDetails.bio,
                    color = LightPrimaryBlackColor,
                    fontFamily = PlusJakartaSans,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                    maxLines = 3,
                )
            }

            val onClick = if (userDetails.isMyProfile) {
                onChangeProfileImage
            } else if (!userDetails.isRequestSend) {
                onSendRequestClick
            } else {
                {}
            }

            ProfileImageWithIcon(
                profilePicture = userDetails.profilePicture,
                onClickIcon = onClick,
                painter = GetPainterProfileIcon(userDetails)
            )
        }

    }
}

@Composable
private fun GetPainterProfileIcon(
    userDetails: UserDetailsUIState
): Painter? {
    return if (userDetails.isMyProfile) {
        painterResource(id = R.drawable.ic_camera)
    } else if (userDetails.isRequestSend) {
        painterResource(id = R.drawable.ic_requsted_add_user)
    } else if (!userDetails.areFriends) {
        painterResource(id = R.drawable.ic_add_user)
    } else {
        null
    }
}
