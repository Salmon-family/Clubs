package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightPrimaryBrandTransparentColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.userProfile.UserDetailsUIState

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileDetailsSection(
    userDetails: UserDetailsUIState,
    modifier: Modifier = Modifier,
    onChangeProfileImage: () -> Unit,
    onSendRequestClick: () -> Unit,
    onClickBack: () -> Unit,
) {
    val context = LocalContext.current

    val motionSceneContent = remember {
        context.resources
            .openRawResource(R.raw.motion_layout_for_profile)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        modifier = modifier
            .fillMaxSize()
            .background(LightPrimaryBrandColor),
        motionScene = MotionScene(motionSceneContent),
    ) {

        val properties = motionProperties(id = "textName")

        Box(modifier = Modifier
            .layoutId("imageCover")
            .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = userDetails.profilePicture,
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

        Box(
            modifier = Modifier
                .layoutId("backButton")
                .padding(16.dp)
                .clickable { onClickBack() },
        ) {
            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
        }

        Text(
            text = userDetails.title,
            modifier = Modifier
                .layoutId("textTitle")
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
                .layoutId("textName")
                .padding(start = 24.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            fontSize = properties.value.fontSize("fontSize"),
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 2
        )

        Box(
            modifier = Modifier
                .layoutId("imageProfile")
                .fillMaxWidth(),
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
                profilePicture = userDetails.profilePicture,
                onClickIcon = onClick,
                painter = GetPainterProfileIcon(userDetails)
            )
        }
    }
}

@Composable
private fun GetPainterProfileIcon(
    userDetails: UserDetailsUIState,
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
