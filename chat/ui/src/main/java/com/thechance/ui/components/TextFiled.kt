package com.thechance.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.spacer.SpaceHorizontal
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.theme.Typography
import com.thechance.ui.theme.WhiteColor

@Composable
fun CustomTextField(
    information: String,
    onTextChange: (String) -> Unit,
    onClickAction: () -> Unit,
    empty: Boolean,
) {
    Card(
        modifier = Modifier.padding(16.dp),
        backgroundColor = WhiteColor,
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.paperclip),
                contentDescription = "paperclip")
            SpaceHorizontal(width = 4)
            BasicTextField(
                value = information,
                onValueChange = onTextChange,
                textStyle = Typography.body1,
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if(empty){
                        Text(text = "Enter your message",
                            style = Typography.body1,
                            color = Color.Black)
                    }
                    innerTextField()
                }
            )
            ButtonSend(onClickAction)
        }
    }
}

@Composable
fun ButtonSend(onClickAction: () -> Unit) {
    Button(
        modifier = Modifier.width(40.dp),
        colors = ButtonDefaults.buttonColors(LightPrimaryBrandColor),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onClickAction,
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.paper_airplane),
            contentDescription = "back button",
            tint = WhiteColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultTextFieldPreview() {
//    CustomTextField()
}