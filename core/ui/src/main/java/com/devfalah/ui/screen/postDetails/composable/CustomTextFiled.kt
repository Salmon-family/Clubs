package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.Typography
import com.devfalah.ui.theme.WhiteColor
import java.io.File

@Composable
fun CustomTextFiled(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
    addImageWithComment: () -> Unit,
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
                style = Typography.body1
            )
        },
        leadingIcon = {
            Image(
                modifier = Modifier.clickable { addImageWithComment() },
                imageVector = ImageVector.vectorResource(id = R.drawable.photo),
                contentDescription = stringResource(R.string.send_comment),
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
    CustomTextFiled(text = "", onValueChanged = {}, sendMessage = {}, addImageWithComment = {})
}