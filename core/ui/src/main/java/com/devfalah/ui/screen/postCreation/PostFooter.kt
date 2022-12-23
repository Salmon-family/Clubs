package com.devfalah.ui.screen.postCreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor


@Composable
fun PostFooter(
    modifier: Modifier = Modifier,
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
            contentDescription = null
        )

        Button(
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = LightPrimaryBrandColor),
            onClick = onClickPost,
        ) {
            Text(
                text = stringResource(id = R.string.post_button),
                color = WhiteColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = PlusJakartaSans,
                fontSize = 14.sp
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewPostFooter() {
    PostFooter(
        onClickPost = {},
        onSelectImage = {}
    )
}