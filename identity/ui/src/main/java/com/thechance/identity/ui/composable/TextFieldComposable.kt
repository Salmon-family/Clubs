package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightCardColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun TextFieldComposable(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        TextComposable(
            text = "Email", style = Typography.body2, color = LightSecondaryBlackColor
        )
        SpacerVertical(height = 14.dp)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = LightCardColor,
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp
        ) {
            BasicTextField(
                value = value,
                modifier = Modifier.padding(16.dp),
                onValueChange = onValueChange,
            )
        }
    }


}