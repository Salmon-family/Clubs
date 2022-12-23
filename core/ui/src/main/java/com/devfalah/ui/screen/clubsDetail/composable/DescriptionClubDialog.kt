package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor

@Composable
fun DescriptionClubDialog(
    descriptionClub: String,
    onPopupDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = { onPopupDismiss() },
    ) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            backgroundColor = WhiteColor
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HeightSpacer16()
                Text(
                    text = stringResource(id = R.string.club_dialog),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    fontFamily = PlusJakartaSans,
                    color = LightPrimaryBlackColor,
                )

                Text(
                    text = descriptionClub,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    fontFamily = PlusJakartaSans,
                    color = LightSecondaryBlackColor,
                )
                HeightSpacer8()
            }
        }
    }
}