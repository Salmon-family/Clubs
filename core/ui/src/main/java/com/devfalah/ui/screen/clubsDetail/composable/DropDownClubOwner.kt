package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.WhiteColor

@Composable
fun DropDownOwner(
    modifier: Modifier,
    onClickJoinRequestClub: () -> Unit,
    onClickEditClub: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.nonRippleEffect { expanded = true },
            painter = painterResource(R.drawable.ic_setting),
            contentDescription = null,
            tint = WhiteColor
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.background,
                ),
            offset = DpOffset(8.dp, 0.dp)
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    expanded = true
                    onClickJoinRequestClub()
                }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.users_requests),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                )
            }
            DropdownMenuItem(onClick = {
                expanded = true
                onClickEditClub()
            }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.edit_club),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                )

            }
        }
    }
}