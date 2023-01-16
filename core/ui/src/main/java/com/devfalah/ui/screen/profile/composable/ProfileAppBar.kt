package com.devfalah.ui.screen.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.flipWithLanguage
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.userProfile.UserDetailsUIState
import com.devfalah.viewmodels.userProfile.UserUIState

@SuppressLint("FrequentlyChangedStateReadInComposition")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileAppBar(
    state: UserUIState,
    lazyScrollState: LazyListState,
    selectedImageUri: Uri?,
    onClickAddFriend: () -> Unit,
    onChangeProfileImage: () -> Unit,
    onEditUserInformation: () -> Unit,
    onClickBackButton: () -> Unit,
    onRemoveFriend: () -> Unit,
) {

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.option_scene).readBytes().decodeToString()
    }
    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemScrollOffset == 0) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemScrollOffset == 0) 280.dp else 60.dp,
        tween(500)
    )

    Box() {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            modifier = Modifier
                .fillMaxWidth()
                .height(motionHeight),
            progress = progress
        ) {

            Box(
                modifier = Modifier
                    .background(LightPrimaryBrandColor)
                    .layoutId("firstBox")
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = selectedImageUri ?: state.userDetails.profilePicture,
                        error = painterResource(id = R.drawable.test_image)
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(motionProperties("imageBox").value.color("background"))
                    .layoutId("imageBox")
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .layoutId("appBar"),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .nonRippleEffect { onClickBackButton() }
                        .flipWithLanguage(),
                    tint = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                if (state.userDetails.isMyProfile) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.setting),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onEditUserInformation()
                            },
                        tint = Color.White
                    )
                }
            }

            Text(
                text = state.userDetails.title,
                modifier = Modifier
                    .layoutId("textTitle"),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                fontFamily = PlusJakartaSans,
                color = WhiteColor,
                maxLines = 1
            )
            Text(
                text = state.userDetails.name,
                modifier = Modifier
                    .layoutId("textName"),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight(motionInt("textName", "fontWeight")),
                fontSize = motionFontSize("textName", "fontSize"),
                fontFamily = PlusJakartaSans,
                color = WhiteColor,
                maxLines = 2
            )

            Box(
                modifier = Modifier.layoutId("boxProfile"),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(60.dp)
                        .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                        .background(MaterialTheme.colors.background),
                )
            }

            Image(
                painter = rememberAsyncImagePainter(
                    model = (selectedImageUri ?: state.userDetails.profilePicture).toString()
                ),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .layoutId("imageProfile")
                    .border(2.dp, MaterialTheme.colors.background, CircleShape),
                contentScale = ContentScale.Crop
            )

            val onClick = if (state.userDetails.isMyProfile) {
                onChangeProfileImage
            } else if (state.userDetails.areFriends) {
                onRemoveFriend
            } else if (!state.userDetails.isRequestSend) {
                onClickAddFriend
            } else {
                {}
            }
            getPainterProfileIcon(state.userDetails)?.let {
                Box(modifier = Modifier
                    .nonRippleEffect {
                        onClick()
                    }
                    .clip(CircleShape)
                    .layoutId("imagePicker")
                    .background(MaterialTheme.colors.surface)
                    .border(4.dp, MaterialTheme.colors.background, CircleShape),
                    contentAlignment = Alignment.Center) {
                    getPainterProfileIcon(state.userDetails)?.let { painter ->
                        Icon(
                            tint = LightPrimaryBrandColor,
                            painter = painter,
                            contentDescription = null
                        )
                    }
                }
            }

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
    } else if (userDetails.areFriends) {
        painterResource(id = R.drawable.delete_user)
    } else {
        null
    }
}