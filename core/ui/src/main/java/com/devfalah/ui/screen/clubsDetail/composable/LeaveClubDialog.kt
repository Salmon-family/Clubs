package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun LeaveClubDialog(
    onDeclineClub: () -> Unit,
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
            backgroundColor = MaterialTheme.colors.surface
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            ) {
                HeightSpacer16()
                Text(
                    text = stringResource(id = R.string.are_you_sure),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = PlusJakartaSans,
                    color = MaterialTheme.colors.primaryVariant,
                )
                HeightSpacer16()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        modifier = Modifier
                            .nonRippleEffect {
                                onPopupDismiss()
                            }
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        fontFamily = PlusJakartaSans,
                        color = MaterialTheme.colors.onSurface,
                    )
                    HeightSpacer16()
                    Text(
                        text = stringResource(id = R.string.confirm),
                        modifier = Modifier
                            .nonRippleEffect {
                                onDeclineClub()
                                onPopupDismiss()
                            }
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        fontFamily = PlusJakartaSans,
                        color = LightPrimaryBrandColor,
                    )
                }
            }
            HeightSpacer16()
        }
    }
}