package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun CustomTextField(
    title: String,
    hint: String? = null,
    value: String,
    maxChar: Int = Int.MAX_VALUE,
    showTextCount: Boolean = false,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Column {
        Row {
            Text(text = title)
            if (showTextCount)
                Text(
                    text = "${value.length} / $maxChar",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                )
        }
        HeightSpacer8()
        TextField(
            value = value,
            onValueChange = {
                if (it.length <= maxChar)
                    onValueChange(it)
            },
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                hint?.let { Text(text = it, textAlign = TextAlign.Center) }
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = singleLine,

            )
    }
}
