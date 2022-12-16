package com.devfalah.ui.screen.postDetails.composable

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState

@Composable
fun CommentHeader(
    user: CommentUIState,
    post: PostDetailsUIState,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        if (post.userId == user.ownerCommentId) {
            if (user.ownerCommentId == post.postDetails.publisherId) {
                PopupMenu(user = user,
                    onClickDeletedComment = onClickDeletedComment,
                    onClickEditComment)
                Spacer(modifier = Modifier.weight(1f))
                UserIdentity(user = user, post = post)
            } else {
                UserIdentity(user = user, post = post)
                Spacer(modifier = Modifier.weight(1f))
                PopupMenu(user = user,
                    onClickDeletedComment = onClickDeletedComment,
                    onClickEditComment)
            }
        } else {
            if (user.ownerCommentId == post.postDetails.publisherId){
                Spacer(modifier = Modifier.weight(1f))
                UserIdentity(user = user, post = post)
            }else{
                UserIdentity(user = user, post = post)
            }
        }

    }

}

@Composable
fun UserIdentity(
    user: CommentUIState,
    post: PostDetailsUIState,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (user.ownerCommentId == post.postDetails.publisherId) {
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
    user: CommentUIState,
) {
    Image(
        painter = rememberAsyncImagePainter(model = user.userImage),
        contentDescription = null,
        Modifier
            .size(40.dp)
            .clip(CircleShape),
    )
}

@Composable
fun TextUserName(
    user: CommentUIState,
) {
    Text(
        text = user.userName,
        fontSize = 14.sp,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.SemiBold,
        color = LightPrimaryBlackColor
    )
}

@Composable
fun PopupMenu(
    user: CommentUIState,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
//                Toast.makeText(context,
//                    "Soon when you decide on the shape of the design",
//                    Toast.LENGTH_SHORT).show()
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