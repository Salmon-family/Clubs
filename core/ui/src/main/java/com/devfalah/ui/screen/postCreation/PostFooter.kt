package com.devfalah.ui.screen.postCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.devfalah.ui.R
import com.devfalah.ui.composable.ButtonWithLoading
import com.devfalah.ui.modifiers.nonRippleEffect


@Composable
fun PostFooter(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isEnabled: Boolean,
    onSelectImage: () -> Unit,
    onClickPost: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.nonRippleEffect { onSelectImage() },
            painter = painterResource(id = R.drawable.ic_gallery_add),
            contentDescription = null,
            tint = MaterialTheme.colors.primaryVariant
        )

//        Button(
//            elevation = ButtonDefaults.elevation(0.dp),
//            shape = RoundedCornerShape(100.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = LightPrimaryBrandColor),
//            onClick = onClickPost,
//        ) {
//            Text(
//                text = stringResource(id = R.string.post_button),
//                color = WhiteColor,
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.SemiBold,
//                fontFamily = PlusJakartaSans,
//                fontSize = 14.sp
//            )
//        }

        ButtonWithLoading(
            text = stringResource(id = R.string.post_button),
            onClick = onClickPost,
            isLoading = isLoading,
            isEnabled = isEnabled,
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewPostFooter() {
    PostFooter(
        onClickPost = {},
        onSelectImage = {},
        isLoading = true,
        isEnabled = false
    )
}