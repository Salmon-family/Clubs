package com.thechance.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thechance.ui.theme.Typography

@Composable
fun AppBar(
    userName: String,
    imageUser: String,
    onCLickBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .size(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackButton(onCLickBack)
        SpaceHorizontal(width = 16)
        ImageUserAvatar(imageUser)
        SpaceHorizontal(width = 8)
        Text(text = userName, style = Typography.subtitle2)
    }
}

@Composable
fun ImageUserAvatar(imageUser: String) {
    AsyncImage(
        model = imageUser,
        contentDescription = "user avatar",
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(30.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultAppBarPreview() {
    AppBar("Chris Evans",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV3uRw6RO8vGPqf4MLmHgXTqyV74h7VBY5ow&usqp=CAU") {}
}