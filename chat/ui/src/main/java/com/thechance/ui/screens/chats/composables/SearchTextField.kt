package com.thechance.ui.screens.chats.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.ui.R
import com.thechance.ui.theme.LightSecondaryBrandColor
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
            focusedIndicatorColor = White,
            unfocusedIndicatorColor = White,
        ),
        value = text,
        shape = RoundedCornerShape(100.dp),
        singleLine = true,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.search_text),
                fontSize = 14.sp,
                color = LightSecondaryBrandColor.copy(alpha = 0.4f),
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