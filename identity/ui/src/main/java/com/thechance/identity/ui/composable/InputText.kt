package com.thechance.identity.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.*

@Composable
fun InputText(
    text: String,
    type: KeyboardType,
    placeHolder: String,
    onTextChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(size = 100.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = WhiteColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = LightPrimaryBlackColor
        ),
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolder,
                style = Typography.InputText,
                color = LightPrimaryGrayColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = type
        ),
    )
}


