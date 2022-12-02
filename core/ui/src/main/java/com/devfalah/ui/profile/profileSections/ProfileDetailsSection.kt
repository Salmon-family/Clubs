package com.devfalah.ui.profile.profileSections

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.devfalah.ui.R
import com.devfalah.ui.profile.VerticalSpacer4
import com.devfalah.ui.profile.VerticalSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun ProfileDetailsSection() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {

            val (imageCover, imageProfile) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.test_profile_cover),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .constrainAs(imageCover) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.test_profile),
                contentDescription = null,
                modifier = Modifier
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

        VerticalSpacer8()

        Text(
            text = "Amnah Hadeed",
            color = LightPrimaryBlackColor,
            fontFamily = PlusJakartaSans,
            fontSize = 18.sp
        )
        Text(
            text = "@amnah",
            color = LightTernaryBlackColor,
            fontFamily = PlusJakartaSans,
            fontSize = 14.sp
        )

        VerticalSpacer8()

        Text(
            text = "Android Developer | UI/UX Designer",
            color = LightPrimaryBrandColor,
            fontFamily = PlusJakartaSans,
            fontSize = 14.sp
        )

        VerticalSpacer4()

        Text(
            text = "Some things here to show the state,Some things here to show the state Some things here to show the state Some things here to show the state",
            color = LightPrimaryBlackColor,
            fontFamily = PlusJakartaSans,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

    }

}