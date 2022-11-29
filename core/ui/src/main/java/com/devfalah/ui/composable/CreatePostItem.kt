package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.Spacer.horizantel.SpacerHorizontal8
import com.devfalah.ui.theme.PurpleLight

@Composable
fun CreatePostItem(
    image: String,
    value: String,
    onTextChange: (String) -> Unit
) {

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        CircleImage(image = image, size = 48)
        SpacerHorizontal8()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(PurpleLight)
        ) {
            Column() {
                TextFieldComposable(
                    value = value,
                    onTextChange = onTextChange
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconComposable(icon = R.drawable.ic_gallary)
                    IconComposable(icon = R.drawable.ic_brush)
                    IconComposable(icon = R.drawable.ic_tag)
                    IconComposable(icon = R.drawable.ic_location)
                }
            }
        }
    }
}