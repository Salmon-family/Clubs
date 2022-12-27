package com.devfalah.ui.composable

import android.graphics.Bitmap
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightCardColor


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
    val isKeyboardOpen by keyboardAsState() // Keyboard.Opened or Keyboard.Closed

    PostTextField(value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        hint = hint,
        isSingleLine = isSingleLine)
    if (isKeyboardOpen == Keyboard.Closed) PostImage(image = image,
        onRemoveImage = onRemoveImage) else Spacer(modifier = Modifier.height(0.dp))


}

@Composable
fun PostTextField(
    value: String,
    modifier: Modifier = Modifier,
    hint: String? = null,
    isSingleLine: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface),
        value = value,
        onValueChange = { onValueChange(it) },
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
                    color = MaterialTheme.colors.onSurface
                )
            }
        },
        maxLines = 100,
        singleLine = isSingleLine,
    )
}

@Composable
fun PostImage(
    modifier: Modifier = Modifier,
    image: Bitmap?,
    onRemoveImage: () -> Unit,
) {
    if (image != null) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .padding(16.dp),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
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


@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}

enum class Keyboard {
    Opened, Closed
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


