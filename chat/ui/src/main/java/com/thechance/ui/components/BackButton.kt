package com.thechance.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.theme.ClubsTheme
import com.thechance.ui.theme.LightPrimaryBrandColor
import com.thechance.ui.theme.LightSecondaryBrandColor

@Composable
fun BackButton() {
    Card(
        modifier = Modifier.size(26.dp),
        backgroundColor = LightSecondaryBrandColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
    ) {
        Image(
            modifier = Modifier.padding(4.dp),
            painter = painterResource(id = R.drawable.back_button),
            contentDescription = "back button")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultBackButtonPreview() {
    ClubsTheme {
        BackButton()
    }
}