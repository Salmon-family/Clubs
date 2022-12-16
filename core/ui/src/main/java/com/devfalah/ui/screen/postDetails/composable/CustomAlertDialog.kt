package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.*

@Composable
fun InputDialogView(
    onDismiss: () -> Unit,
    onExit: () -> Unit,
    text: String,
    onValueChanged: (String) -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(8.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Edit Comment",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = WhiteColor,
                        focusedIndicatorColor = WhiteColor,
                        unfocusedIndicatorColor = WhiteColor,
                    ),
                    value = text,
                    onValueChange = onValueChanged,
                    placeholder = {
                        Text(
                            text = "Enter your comment...",
                            color = LightTernaryBlackColor,
                            style = Typography.body1
                        )
                    },
//                    leadingIcon = {
//                        Image(
//                            imageVector = ImageVector.vectorResource(id = R.drawable.photo),
//                            contentDescription = stringResource(R.string.send_comment),
//                        )
//                    }
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomButtonDialog(
                        modifier = Modifier.weight(1f),
                        titleButton = "Cancel",
                        titleColor = LightSecondaryBlackColor,
                        backgroundColor = LightBackgroundColor,
                        onClick = onExit
                    )
                    WidthSpacer8()
                    CustomButtonDialog(
                        modifier = Modifier.weight(1f),
                        titleButton = "Edit",
                        backgroundColor = LightPrimaryBrandColor,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}

@Composable
fun CustomButtonDialog(
    modifier: Modifier = Modifier,
    titleButton: String,
    titleColor: Color = WhiteColor,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = titleColor
        )
    ) {
        Text(text = titleButton, style = Typography.button)
    }
}