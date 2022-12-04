package com.thechance.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.theme.ClubsTheme

@Composable
fun BackButton() {
    Image(
        modifier = Modifier.padding(4.dp),
        painter = painterResource(id = R.drawable.arrow_icon),
        contentDescription = "back button")

}

@Preview(showBackground = true)
@Composable
fun DefaultBackButtonPreview() {
    ClubsTheme {
        BackButton()
    }
}