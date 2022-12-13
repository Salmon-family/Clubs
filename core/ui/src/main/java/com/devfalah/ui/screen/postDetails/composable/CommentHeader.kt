package com.devfalah.ui.screen.postDetails.composable

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    user: CommentUIState,
//    onClickDeletedComment: (CommentUIState) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = user.userImage),
                contentDescription = null,
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
            WidthSpacer8()
            Text(
                text = user.userName,
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                color = LightPrimaryBlackColor
            )
        }

        Box {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                    Text("Edit Comment")
                }
                DropdownMenuItem(onClick = { /*onClickDeletedComment(user)*/ }) {
                    Text("Delete Comment")
                }
            }
        }
    }
}