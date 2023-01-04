package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.util.htmlText

@Composable
fun CustomTextField(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    maxChar: Int = Int.MAX_VALUE,
    showTextCount: Boolean = false,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = RoundedCornerShape(100.dp),
    maxLines: Int = 1
) {
    Column {
        Row {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = PlusJakartaSans,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.primaryVariant
                )
            )
            if (showTextCount)
                Text(
                    text = "${value.length} / $maxChar",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                )
        }
        HeightSpacer8()
        TextField(
            value = value.htmlText(),
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
                textColor = MaterialTheme.colors.primaryVariant
            ),
            placeholder = {
                hint?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = MaterialTheme.colors.onSecondary,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
            },
            shape = if (value.length > 35) RoundedCornerShape(20.dp) else shape,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            maxLines = maxLines
        )
    }
}
