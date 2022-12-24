package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor

@Composable
fun CustomTextFiled(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onValueChanged: (String) -> Unit,
    sendMessage: () -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth().shadow(elevation = 4.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        maxLines = 3,
        value = text,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = "Add a comment...",
                color = MaterialTheme.colors.secondaryVariant,
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
    isEnabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .width(40.dp)
            .padding(2.dp),
        colors = ButtonDefaults
            .buttonColors(
                LightPrimaryBrandColor,
                disabledBackgroundColor = MaterialTheme.colors.secondary
            ),
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        enabled = isEnabled,
        contentPadding = PaddingValues(0.dp),
        onClick = onClickAction
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.paper_airplane),
            contentDescription = "back button",
            tint = if (isEnabled) WhiteColor else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultTextFieldPreview() {
    CustomTextFiled(text = "", onValueChanged = {}, sendMessage = {})
}
