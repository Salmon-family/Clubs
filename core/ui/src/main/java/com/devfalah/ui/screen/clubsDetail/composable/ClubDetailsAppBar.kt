package com.devfalah.ui.screen.clubsDetail.composable

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.devfalah.ui.modifiers.flipWithLanguage
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.ui.util.htmlText
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@SuppressLint("FrequentlyChangedStateReadInComposition")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun ClubDetailsAppBar(
    state: ClubDetailsUiState,
    lazyScrollState: LazyListState,
    onBack: () -> Unit,
    maxLineContentExpand: Int = 2,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit
) {
    var popupController by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.option_scene).readBytes().decodeToString()
    }
    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemScrollOffset == 0) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemScrollOffset == 0) 160.dp else 60.dp,
        tween(500)
    )
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .height(motionHeight),
        progress = progress
    ) {

        BackButton(
            modifier = Modifier
                .wrapContentSize()
                .nonRippleEffect { onBack() }
                .layoutId("backClubButton")
                .flipWithLanguage(),
            tint = WhiteColor
        )

        Box(
            modifier = Modifier
                .layoutId("clubDropDown")
        ) {
            if (state.detailsUiState.isOwner) {
                DropDownOwner(
                    modifier = Modifier
                        .layoutId("clubDropDown"),
                    onClickJoinRequestClub = onClickJoinRequestClub,
                    onClickEditClub = onClickEditClub
                )
            }
        }

        Text(
            text = state.detailsUiState.name.htmlText(),
            modifier = Modifier
                .layoutId("clubName"),
            textAlign = TextAlign.Center,
            fontWeight =  FontWeight(motionInt("clubName", "fontWeight")),
            fontSize = motionFontSize("clubName", "fontSize"),
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 1
        )

        ReadMorePopup(
            text = state.detailsUiState.description.htmlText(),
            modifier = Modifier
                .layoutId("clubDescription"),
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
    }
}
