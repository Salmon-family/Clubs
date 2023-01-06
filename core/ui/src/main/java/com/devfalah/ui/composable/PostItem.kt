package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.ui.screen.profile.composable.PostBottomAction
import com.devfalah.ui.screen.profile.composable.PostContent
import com.devfalah.ui.screen.profile.composable.PostHeader
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    state: PostUIState,
    maxLineContentExpand: Int = 2,
    isContentExpandable: Boolean,
    isClubPost: Boolean,
    showGroupName: Boolean = false,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit,
    onClickPostSetting: (PostUIState) -> Unit,
    onClickProfile: (Int) -> Unit,
    onOpenLinkClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            PostHeader(
                state,
                onClickPostSetting = onClickPostSetting,
                onClickProfile = onClickProfile,
                isMyProfile = state.isMyPost,
                hidePrivacy = isClubPost,
                showGroupName = showGroupName,
            )

            PostContent(
                post = state,
                maxLineToExpand = maxLineContentExpand,
                contentExpandable = isContentExpandable,
                onOpenLinkClick = onOpenLinkClick,
                onImageClick = onImageClick
            )

            PostBottomAction(state, onClickLike, onClickComment, onClickSave)
        }
    }
}

