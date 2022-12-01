package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.LightPrimaryBrandColor

@Composable
fun OnboardButton(text: String){
    Button(
        onClick = { },
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(start = 24.dp),
        shape = RoundedCornerShape(size = 20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightPrimaryBrandColor),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = Color.White,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 35.dp)
        )
    }
}