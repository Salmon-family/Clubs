package com.devfalah.ui.screen.profile.composable

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightPrimaryBrandTransparentColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

@Composable
fun ProfileDetailsSection(
    userDetails: UserDetailsUIState,
    selectedImageUri: Uri?,
    modifier: Modifier = Modifier,
    onChangeProfileImage: () -> Unit,
    onSendRequestClick: () -> Unit,
    onClickBackButton: () -> Unit,
    onClickEditProfile: () -> Unit,
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(LightPrimaryBrandColor)
    ) {

        val (appBar, imageCover, imageProfile, textTitle, textName) = createRefs()

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
                    model = selectedImageUri ?: userDetails.profilePicture,
                    error = painterResource(id = R.drawable.test_image)
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightPrimaryBrandTransparentColor)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .constrainAs(appBar){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                modifier = Modifier.size(24.dp).clickable { onClickBackButton() },
                tint = Color.White
            )
            if(userDetails.isMyProfile){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).clickable { onClickEditProfile() },
                    tint = Color.White
                )
            }
        }

        Text(
            text = userDetails.title,
            modifier = Modifier
                .constrainAs(textTitle) {
                    top.linkTo(appBar.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 24.dp, top = 56.dp),
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
                    .background(MaterialTheme.colors.background),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "",
                    color = MaterialTheme.colors.primaryVariant,
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
                profilePicture = (selectedImageUri ?: userDetails.profilePicture).toString(),
                onClickIcon = onClick,
                painter = getPainterProfileIcon(userDetails),
                contentScale = ContentScale.Crop
            )
        }

    }
}


@Composable
private fun getPainterProfileIcon(
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
