package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.clubDetails.ClubDetailsUiState

@Composable
fun ClubHeaderDetails(
    state: ClubDetailsUiState,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPrimaryBrandColor)
    ) {

        val (backButton, textTitle, textName, cover) = createRefs()

        BackButton(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .constrainAs(backButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {

        }

        Text(
            text = state.description,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textTitle) {
                    top.linkTo(backButton.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 50.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 3
        )
        Text(
            text = state.name,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textName) {
                    top.linkTo(textTitle.bottom)
                    start.linkTo(textTitle.start)
                },
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            fontFamily = PlusJakartaSans,
            color = WhiteColor,
            maxLines = 2
        )

        Box(
            modifier = Modifier
                .constrainAs(cover) {
                    top.linkTo(textName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(170.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
                    .background(LightBackgroundColor),
                contentAlignment = Alignment.Center
            ) {

                Row {
                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_menu_language,
                        text = state.privacy
                    )

                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_people,
                        text = state.membersCount.toString()
                    )

                    ClubCard(
                        imageVector = com.devfalah.ui.R.drawable.ic_comment,
                        text = state.postCount.toString()
                    )

                }

            }
        }

    }

}