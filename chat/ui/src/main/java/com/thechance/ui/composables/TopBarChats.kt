package com.thechance.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.ui.R

@Composable
fun TopBarChats() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (arrowBack, title, add) = createRefs()

        ArrowBack(modifier = Modifier.constrainAs(arrowBack) {
            top.linkTo(parent.top, 14.dp)
            start.linkTo(parent.start, 16.dp)
        }, icon = R.drawable.arrow_icon, contentDescription = R.string.arrow_back)

        TopBarTitle(title = R.string.chats, modifier = Modifier.constrainAs(title) {
            top.linkTo(parent.top, 14.dp)
            start.linkTo(arrowBack.end, 16.dp)
        })

        ArrowBack(icon = R.drawable.edit,
            contentDescription = R.string.edit,
            modifier = Modifier.constrainAs(add) {
                top.linkTo(parent.top, 16.dp)
                end.linkTo(parent.end, 16.dp)
            })

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTopBarChats() {
    TopBarChats()
}