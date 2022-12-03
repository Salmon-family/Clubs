package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.*

@Composable
fun InputTextComposable(
    text: String,
    type: KeyboardType,
    placeHolder: String,
    image: Int = 0,
    onTextChange: (String) -> Unit,
    onClick: () -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(size = 20.dp),
        trailingIcon = {
            if (image != 0) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(18.dp)
                        .clickable { onClick }
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightCardColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = LightPrimaryBlackColor
        ),
        placeholder = {
            TextComposable(
                text = placeHolder,
                style = Typography.InputText,
                color = LightPrimaryGrayColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = type
        )
    )
}


