package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.ui.R
import com.thechance.ui.theme.BlackColor

@Composable
fun TopBarChats(
    onCLickBack: ()->Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackButton(onCLickBack)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.chats),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlackColor,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTopBarChats() {
    TopBarChats({})
}