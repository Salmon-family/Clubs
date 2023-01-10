package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.PlusJakartaSans

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordInputText(
    title: String,
    password: String,
    onTextChange: (String) -> Unit,
    isErrorTextShown: Boolean = false
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primaryVariant
            )
        )
        HeightSpacer8()
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onTextChange,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primaryVariant,
                textDirection = TextDirection.Content
            ),
            shape = RoundedCornerShape(size = 100.dp),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.nonRippleEffect { passwordVisible = !passwordVisible },
                    painter = painterResource(
                        id = if (passwordVisible) {
                            R.drawable.ic_remove_eye
                        } else {
                            R.drawable.ic_hide
                        }
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondaryVariant
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password_place_holder),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = PlusJakartaSans,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )

        if (isErrorTextShown) {
            Text(
                text = stringResource(R.string.wrong_password_message),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red
                )
            )
        }
    }
}