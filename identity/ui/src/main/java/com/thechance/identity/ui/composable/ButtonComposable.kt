package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBrandColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.theme.WhiteColor


@Composable
fun ButtonComposable(
    onClick: () -> Unit,
    text: String,
    buttonColor: Color = LightPrimaryBrandColor,
    textColor: Color = WhiteColor,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 23.dp)
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = Typography.body1,
            color = textColor
        )
    }
}