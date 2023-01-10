package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickDeleteComment: (CommentUIState) -> Unit,
    onClickProfile: (Int) -> Unit
) {

    fun Modifier.flipIfNotOwner() =
        this.scale(scaleX = if (state.isOwnerComment) 1f else -1f, scaleY = 1f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .flipIfNotOwner(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (state.isOwnerComment) CommentSetting(
            modifier = Modifier.weight(1f),
            state = state,
            onClickDeleteComment = onClickDeleteComment
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .flipIfNotOwner(),
            text = state.userName,
            textAlign = if (state.isOwnerComment) TextAlign.End else TextAlign.Start,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )

        WidthSpacer8()

        CircleImage(
            modifier = Modifier
                .nonRippleEffect { onClickProfile(state.userId) }
                .flipIfNotOwner(),
            painter = rememberAsyncImagePainter(
                model = state.userPictureUrl,
                error = rememberAsyncImagePainter(model = R.drawable.test_image)
            ),
            size = 32
        )
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
            modifier = Modifier
                .size(20.dp)
                .nonRippleEffect { expanded = true },
            painter = painterResource(R.drawable.ic_setting),
            contentDescription = null,
            tint = MaterialTheme.colors.secondaryVariant
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
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondaryVariant
            )

        }
    }
}