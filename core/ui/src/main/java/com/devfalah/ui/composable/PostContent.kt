package com.devfalah.ui.composable

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightCardColor
import com.devfalah.ui.theme.PlusJakartaSans

@Composable
fun PostContent(
    value: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    image: Bitmap?,
    isSingleLine: Boolean = false,
    onValueChange: (String) -> Unit,
    onRemoveImage: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        item {


            TextField(
                modifier = if (image == null) {
                    Modifier.fillParentMaxSize()
                } else {
                    Modifier.fillMaxSize()
                },
                value = value,
                onValueChange = { onValueChange(it) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    textColor = MaterialTheme.colors.primaryVariant
                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontFamily = PlusJakartaSans,
                    fontSize = 14.sp,
                    textDirection = TextDirection.Content
                ),
                placeholder = {
                    hint?.let {
                        Text(
                            text = it,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = MaterialTheme.colors.onSecondary,
                                fontWeight = FontWeight.Normal,
                                fontFamily = PlusJakartaSans,
                                fontSize = 14.sp
                            )
                        )
                    }
                },
                maxLines = 100,
                singleLine = isSingleLine,
            )
        }

        if (image != null) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(20.dp)),
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Icon(
                        modifier = Modifier
                            .nonRippleEffect { onRemoveImage() }
                            .padding(16.dp)
                            .size(24.dp)
                            .align(Alignment.TopEnd),
                        painter = painterResource(id = R.drawable.remove_image),
                        contentDescription = null,
                        tint = LightCardColor
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewPostContent() {
    PostContent(
        value = "",
        hint = "",
        onRemoveImage = {},
        onValueChange = {},
        image = null
    )

}


