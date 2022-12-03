package com.thechance.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.theme.LightSecondaryBrandColor
import com.thechance.ui.theme.White
import com.thechance.ui.theme.lightSilver

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = White,
            unfocusedIndicatorColor = White,
        ),
        value = text,
        shape = RoundedCornerShape(32.dp),
        singleLine = true,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = "Search for a friend",
                color = LightSecondaryBrandColor.copy(alpha = 0.4f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = lightSilver
            )
        },
    )
}