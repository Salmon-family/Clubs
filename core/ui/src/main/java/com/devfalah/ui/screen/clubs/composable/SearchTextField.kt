package com.devfalah.ui.screen.clubs.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.Typography

@Composable
fun SearchTextField(
    value : String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChanged(it) },
        singleLine = true,
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search_outline),
                contentDescription = "search icon"
            )

        },

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = LightCardBackgroundColor,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),

        placeholder = {
            Text(text = stringResource(R.string.search_for_club),
                style = Typography.body2,
                color = LightTernaryBlackColor
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    SearchTextField(value = "", onValueChanged = {})
}