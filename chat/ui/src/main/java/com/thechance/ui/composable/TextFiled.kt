package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.theme.LightTernaryBlackColor
import com.thechance.ui.theme.Typography
import com.thechance.ui.theme.WhiteColor

@Composable
fun SendTextField(
    modifier: Modifier = Modifier,
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
        maxLines = 4,
        value = text,
        shape = RoundedCornerShape(16.dp),
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = "Enter your message...",
                color = LightTernaryBlackColor,
                style = Typography.body1
            )
        },
        leadingIcon = {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.paperclip),
                contentDescription = stringResource(R.string.chats),
            )
        },
        trailingIcon = {
            ButtonSend(onClickAction = sendMessage)
        }
    )
}

@Composable
fun ButtonSend(onClickAction: () -> Unit) {
    Button(

        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        colors = ButtonDefaults.buttonColors(LightPrimaryBrandColor),
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.elevation(0.dp),
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
//    CustomTextField("", {}, {}, )
    SendTextField(text = "", onValueChanged = {}, sendMessage = {})
}