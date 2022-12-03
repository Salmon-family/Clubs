package com.devfalah.ui.screens.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.PrimaryColor
import com.devfalah.ui.theme.Purple50

@Composable
fun BackButton(
    backButtonClick: () -> Unit
){
    Card(
        modifier = Modifier
            .size(32.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Purple50,
        elevation = 0.dp
    ){
        IconButton(onClick = backButtonClick,) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "BackButton",
                tint = PrimaryColor
            )
        }
    }
}