package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.*

@Composable
fun InputTextComposable(
    type: KeyboardType,
    image:Int = 0,
    onClick: () -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        shape = RoundedCornerShape(size = 20.dp),
        trailingIcon = {
            if (image != 0){
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
                text = stringResource(id = R.string.email_place_holder),
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

