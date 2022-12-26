package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickDeleteComment: (CommentUIState) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.isOwnerComment) {

            CommentSetting(
                modifier = Modifier.weight(1f),
                state = state,
                onClickDeleteComment = onClickDeleteComment
            )

            Text(
                modifier = modifier,
                text = state.userName,
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            WidthSpacer8()

            CircleImage(
                painter = rememberAsyncImagePainter(
                    model = state.userPictureUrl,
                    error = rememberAsyncImagePainter(model = R.drawable.test_image)
                ),
                size = 32
            )

        } else {

            CircleImage(
                painter = rememberAsyncImagePainter(
                    model = state.userPictureUrl,
                    error = rememberAsyncImagePainter(model = R.drawable.test_image)
                ),
                size = 32
            )

            WidthSpacer8()

            Text(
                text = state.userName,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }

    }
}


@Composable
fun CommentSetting(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickDeleteComment: (CommentUIState) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            modifier = Modifier.nonRippleEffect { expanded = true },
            painter = painterResource(R.drawable.ic_setting),
            contentDescription = null
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.background(MaterialTheme.colors.background),
    ) {
        DropdownMenuItem(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                expanded = false
                onClickDeleteComment(state)
            }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.delete),
                textAlign = TextAlign.Center
            )

        }
    }
}