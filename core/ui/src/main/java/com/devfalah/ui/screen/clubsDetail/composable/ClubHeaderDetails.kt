package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.ui.util.htmlText
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ClubHeaderDetails(
    state: ClubDetailsUiState,
    onBack: () -> Unit,
    maxLineContentExpand: Int = 2,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit,
) {
    var popupController by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val motionSceneContent = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionSceneContent),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
    ) {
        val properties = motionProperties(id = "nameClub")
        BackButton(
            modifier = Modifier
                .layoutId("backButton")
                .wrapContentSize()
                .nonRippleEffect { onBack() },
            tint = WhiteColor
        )

        if (state.detailsUiState.isOwner) {
            DropDownOwner(
                modifier = Modifier
                    .layoutId("dropDownMenu"),
                onClickJoinRequestClub = onClickJoinRequestClub,
                onClickEditClub = onClickEditClub
            )
        }

        Text(
            text = state.detailsUiState.name,
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("nameClub"),
            fontWeight = FontWeight.SemiBold,
            fontSize = properties.value.fontSize("fontSize"),
            fontFamily = PlusJakartaSans,
            textAlign = TextAlign.Center,
            color = WhiteColor,
            maxLines = 1
        )

        ReadMorePopup(
            text = state.detailsUiState.description.htmlText(),
            modifier = Modifier
                .layoutId("descriptionClub"),
            minimizedMaxLines = maxLineContentExpand,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                color = WhiteColor,
            ),
            onShowDescription = { popupController = true }
        )

        if (popupController) {
            DescriptionClubDialog(
                descriptionClub = state.detailsUiState.description.htmlText(),
            ) {
                popupController = false
            }
        }
        Box(
            modifier = Modifier
                .layoutId("cover")
                .fillMaxWidth(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layoutId("shapeRound")
                .background(MaterialTheme.colors.onBackground)
                .shadow(
                    shape = RoundedCornerShape(100.dp, 100.dp, 0.dp, 0.dp),
                    elevation = 0.dp,
                ),
        )
    }
}