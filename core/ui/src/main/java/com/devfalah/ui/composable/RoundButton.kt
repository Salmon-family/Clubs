package com.devfalah.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    userState: UserState,
    onButtonClick: (Int) -> Unit,
    buttonColor: Color = LightPrimaryBrandColor,
    text: String,
    textColor: Color,
    roundCorner: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Button(
        onClick = { onButtonClick(userState.userID) },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(roundCorner.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
    }
}