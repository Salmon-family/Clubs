package com.thechance.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.ui.R
import com.thechance.ui.theme.PlusJakartaSans

@Composable
fun TopBarChats(
    onCLickBack: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.chats),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PlusJakartaSans
            )
        },
        navigationIcon = {
            BackButton(onClick = onCLickBack)
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.Black,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true, locale = "en")
@Preview(showBackground = true, showSystemUi = true, locale = "ar")
@Composable
fun PreviewTopBarChats() {
    TopBarChats({})
}