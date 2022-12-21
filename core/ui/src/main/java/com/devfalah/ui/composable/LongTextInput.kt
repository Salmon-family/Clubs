package com.devfalah.ui.composable

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.theme.LightTernaryBlackColor

@Composable
fun PostContent(
    value: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    maxChar: Int = Int.MAX_VALUE,
    isSingleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    image: Bitmap?
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
    ) {

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(3f),
            value = value,
            onValueChange = {
                if (it.length <= maxChar)
                    onValueChange(it)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                hint?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        color = LightTernaryBlackColor
                    )
                }
            },
            maxLines = 5,
            singleLine = isSingleLine,
        )
        if (image != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .padding(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(20.dp)),
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }

    }
}