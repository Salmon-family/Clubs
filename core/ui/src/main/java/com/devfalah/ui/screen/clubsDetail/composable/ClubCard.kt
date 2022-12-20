package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor

@Composable
fun ClubCard(
    imageVector: Int,
    text: String
) {

    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(80.dp)
            .background(
                shape = RoundedCornerShape(16.dp), color = WhiteColor
            ),
        contentAlignment = Alignment.Center
    ) {


        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = imageVector),
                contentDescription = "imageVector",
                tint = LightTernaryBlackColor
            )

            Text(
                text = text,
                modifier = Modifier.padding(top = 4.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                color = LightPrimaryBrandColor,
            )

        }
    }

}