package com.devfalah.ui.screen.postDetails.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.AppTypography
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    state: CommentUIState,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (state.isOwnerComment) {
            PopupMenu(
                user = state,
                onClickDeletedComment = onClickDeletedComment,
                onClickEditComment
            )
            Spacer(modifier = Modifier.weight(1f))
            UserIdentity(user = state)
        } else {
            UserIdentity(user = state)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun UserIdentity(
    user: CommentUIState
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (user.isOwnerComment) {
            TextUserName(user = user)
            WidthSpacer8()
            ImageAvatar(user = user)
        } else {
            ImageAvatar(user = user)
            WidthSpacer8()
            TextUserName(user = user)
        }
    }
}

@Composable
fun ImageAvatar(
    user: CommentUIState
) {
    Image(
        painter = rememberAsyncImagePainter(model = user.userImage),
        contentDescription = null,
        Modifier
            .size(40.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TextUserName(
    user: CommentUIState
) {
    Text(
        text = user.userName,
        style = AppTypography.subtitle2,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun PopupMenu(
    user: CommentUIState,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                onClickEditComment(user)
            }) {
                Text("Edit Comment")
            }
            DropdownMenuItem(onClick = { onClickDeletedComment(user) }) {
                Text("Delete Comment")
            }
            Log.i("CommentHeader", "PopupMenu: ${user.isEdited}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentHeaderPreview() {
    CommentHeader(
        state = CommentUIState(
            userName = "Omar Ezzdeen",
            userImage = "https://avatars.githubusercontent.com/u/45457224?v=4",
            ownerCommentId = 1,
            isEdited = false
        ),
        onClickDeletedComment = {},
        onClickEditComment = {}
    )
}
