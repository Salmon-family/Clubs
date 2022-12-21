package com.devfalah.ui.composable

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.LightTernaryBlackColor

@Composable
fun LongTextInput(
    value: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    maxChar: Int = Int.MAX_VALUE,
    isSingleLine: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = {
            if (it.length <= maxChar)
                onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5f),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            hint?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    color = LightTernaryBlackColor
                )
            }
        },
        maxLines = 5,
        shape = RoundedCornerShape(20.dp),
        singleLine = isSingleLine,
    )
}