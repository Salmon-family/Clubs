package com.thechance.identity.ui.screen.login.username

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.theme.WhiteColor

@Composable
fun ContinueButton(
    state: String,
    onClick: (String) -> Unit,
    isEnabled:Boolean = true,
    text: String,
    buttonColor: Color = LightPrimaryBrandColor,
    textColor: Color = WhiteColor,
    buttonModifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 23.dp),
    textModifier: Modifier = Modifier.padding(8.dp)
) {
    Button(
        modifier = buttonModifier
            .clip(RoundedCornerShape(100.dp)),
        onClick = {onClick(state)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        ),
        enabled = isEnabled,
    ) {
        Text(
            modifier = textModifier,
            text = text,
            style = Typography.body1,
            color = textColor
        )
    }
}