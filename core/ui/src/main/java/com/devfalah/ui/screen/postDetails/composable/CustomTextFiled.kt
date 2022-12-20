package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.WhiteColor

@Composable
fun CustomTextFiled(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = WhiteColor,
            focusedIndicatorColor = WhiteColor,
            unfocusedIndicatorColor = WhiteColor,
        ),
        maxLines = 3,
        value = text,
        shape = RoundedCornerShape(16.dp),
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = "Enter your comment...",
                color = LightTernaryBlackColor,
                style = AppTypography.body1
            )
        },
        trailingIcon = {
            ButtonSend(onClickAction = sendMessage, isEnabled)
        }
    )
}

@Composable
fun ButtonSend(
    onClickAction: () -> Unit,
    isEnabled: Boolean = true,
) {
    Button(
        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(LightPrimaryBrandColor),
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        enabled = isEnabled,
        contentPadding = PaddingValues(0.dp),
        onClick = onClickAction,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.paper_airplane),
            contentDescription = "back button",
            tint = WhiteColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultTextFieldPreview() {
    CustomTextFiled(text = "", onValueChanged = {}, sendMessage = {})
}