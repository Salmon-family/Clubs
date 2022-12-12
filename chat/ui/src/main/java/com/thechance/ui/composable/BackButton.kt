package com.thechance.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.ui.R
import com.thechance.ui.theme.ClubsTheme

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(top = 5.dp)
    ) {
        Icon(ImageVector.vectorResource(id = R.drawable.arrow_icon), "backIcon")
    }
}

@Preview(showBackground = true, locale = "en")
@Preview(showBackground = true, locale = "ar")
@Composable
fun DefaultBackButtonPreview() {
    ClubsTheme {
        BackButton({})
    }
}