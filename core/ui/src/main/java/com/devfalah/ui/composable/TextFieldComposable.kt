package com.devfalah.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldComposable(
    value: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onTextChange,
        decorationBox = {
            Text(
                text = "What do you want to talk about?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    )
}