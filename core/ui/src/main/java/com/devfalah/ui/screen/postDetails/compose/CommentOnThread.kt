package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.WhiteColor

@Composable
fun CommentOnThread(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    text: String = "",
    onValueChanged: (String) -> Unit,
    onClickSendComment: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colors.background)
            .fillMaxWidth(),
    ) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            maxLines = 3,
            value = text,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_your_comment),
                    color = LightTernaryBlackColor,
                    style = AppTypography.body1
                )
            },
            trailingIcon = {
                Button(
                    modifier = Modifier
                        .width(40.dp)
                        .padding(2.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = LightPrimaryBrandColor),
                    shape = RoundedCornerShape(100.dp),
                    enabled = isEnabled,
                    contentPadding = PaddingValues(0.dp),
                    onClick = onClickSendComment,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = null,
                        tint = if (isEnabled) WhiteColor else Color.Gray,
                    )
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCommentOnThread() {
//    CommentOnThread()
}
