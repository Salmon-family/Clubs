package com.devfalah.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.TextBlackColor

@Composable
fun TopBarComposable(
    title: String
){
    TopAppBar(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            fontFamily = PlusJakartaSans,
            color = TextBlackColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }
}

/*
 Image(
                painter = painterResource(id = R.drawable.ic_chat),
                contentDescription = null
            )
 */