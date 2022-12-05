package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun ProfilePostItem(
    post: PostUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PostHeader(post)
        PostContent(post)
        PostBottomAction(post, onClickLike, onClickComment, onClickSave)
    }
}