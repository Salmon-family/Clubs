package com.thechance.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.ui.theme.BlackColor

@Composable
fun TopBarTitle() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val title = createRef()
        Text(modifier = Modifier.constrainAs(title){
                                                   start.linkTo(parent.start, 16.dp)
            top.linkTo(parent.top, 12.dp)
            bottom.linkTo(parent.bottom, 12.dp)
        },text = "Chats", fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = BlackColor)
    }
}