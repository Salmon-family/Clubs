package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.ui.R
import com.thechance.ui.theme.LightSecondaryBlackColor
import com.thechance.ui.theme.PlusJakartaSans
import com.thechance.ui.theme.White

@Composable
fun SearchTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            ),
        value = text,
        shape = RoundedCornerShape(100.dp),
        singleLine = true,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.search_text),
                fontSize = 14.sp,
                color = LightSecondaryBlackColor.copy(alpha = 0.4f),
                fontFamily = PlusJakartaSans
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription =null,
            )
        },
    )
}