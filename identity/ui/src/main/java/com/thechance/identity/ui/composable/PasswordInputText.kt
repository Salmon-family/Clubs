package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.InputText
import com.thechance.identity.ui.theme.Typography

@Composable
fun PasswordInputText(
    text: String,
    placeHolder: String,
    match: Boolean = false,
    onTextChange: (String) -> Unit,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        value = text,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(size = 100.dp),
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                if (match) {
                    TrailingIconPassword(icon = R.drawable.ic_match)
                } else {
                    if (passwordVisible) {
                        TrailingIconPassword(icon = R.drawable.ic_remove_eye)
                    } else {
                        TrailingIconPassword(icon = R.drawable.ic_hide)
                    }
                }
            }

        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = MaterialTheme.colors.primaryVariant
        ),
        singleLine = true,
        textStyle = TextStyle(
            textDirection = TextDirection.Content
        ),
        placeholder = {
            Text(
                text = placeHolder,
                style = Typography.InputText,
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

@Composable
private fun TrailingIconPassword(
    icon: Int,
) {
    Icon(
        painter = painterResource(id = icon),
        "password",
        tint = MaterialTheme.colors.secondaryVariant
    )

}

