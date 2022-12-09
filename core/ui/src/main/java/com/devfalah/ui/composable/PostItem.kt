package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.ui.screen.profile.composable.PostBottomAction
import com.devfalah.ui.screen.profile.composable.PostContent
import com.devfalah.ui.screen.profile.composable.PostHeader
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    state: PostUIState,
    isMyProfile: Boolean,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickPostSetting: (PostUIState) -> Unit,
    maxLineContentExpand: Int = 2,
    isContentExpandable: Boolean
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        backgroundColor = WhiteColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            PostHeader(state, onClickPostSetting = onClickPostSetting, isMyProfile = isMyProfile)
            PostContent(
                post = state,
                maxLineToExpand = maxLineContentExpand,
                contentExpandable = isContentExpandable
            )
            PostBottomAction(state, onClickLike, onClickComment, onClickSave)
        }
    }
}