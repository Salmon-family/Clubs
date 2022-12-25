package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.DeletePostUseCase
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.SetFavoritePostUseCase
import com.devfalah.usecases.SetPostLikeUseCase
import com.devfalah.usecases.posts.GetPostCommentsUseCase
import com.devfalah.usecases.posts.GetPostDetailsUseCase
import com.devfalah.usecases.posts.MangeCommentUseCase
import com.devfalah.usecases.posts.SetCommentLikeUseCase
import com.devfalah.usecases.util.Constants.HOME_GROUP_ID
import com.devfalah.viewmodels.postDetails.mapper.toUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    val getUserIdUseCase: GetUserIdUseCase,
    val getPostCommentsUseCase: GetPostCommentsUseCase,
    val getPostDetailsUseCase: GetPostDetailsUseCase,
    val mangeComment: MangeCommentUseCase,
    val commentLike: SetCommentLikeUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val postLike: SetPostLikeUseCase,
    val deletePostUseCase: DeletePostUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = PostDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(PostDetailsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(id = getUserIdUseCase()) }
                getPostDetails()
                getPostComments()
            } catch (t: Throwable) {

            }
        }
    }

    //region Post
    private fun getPostDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val post = getPostDetailsUseCase(args.postId, uiState.value.id)
                _uiState.update {
                    it.copy(
                        post = post.toUIState(HOME_GROUP_ID)
                            .copy(
                                isSaved = args.isSaved,
                                publisherId = args.publisherId,
                                publisherImage = args.publisherImageUrl,
                                publisherName = args.publisherName
                            ),
                        isLoading = false
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    fun onClickLikePost(post: PostUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(error = "") }
            try {
                val totalLikes = postLike(uiState.value.id, args.postId, post.isLikedByUser)
                _uiState.update {
                    it.copy(
                        post = post.copy(
                            isLikedByUser = !post.isLikedByUser,
                            totalLikes = totalLikes
                        )
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    fun onClickSavePost(post: PostUIState) {
        viewModelScope.launch {
            try {
                favoritePostUseCase(post.toEntity())
                _uiState.update { it.copy(post = it.post.copy(isSaved = !post.isSaved)) }
            } catch (t: Throwable) {
                t.message.toString()
            }
        }
    }

    fun onDeletePost(post: PostUIState) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                if (deletePostUseCase(_uiState.value.id, post.postId)) {
                    _uiState.update { it.copy(isPostDeleted = true) }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }
    //endregion

    //region Comment
    fun onCommentValueChanged(comment: String) {
        _uiState.update { it.copy(commentText = comment) }
    }

    fun onClickLikeComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(minorError = "") }
            try {
                val totalLike = commentLike(
                    userId = uiState.value.id,
                    commentId = comment.id,
                    isLiked = comment.isLikedByUser
                )
                val comments = uiState.value.comments.map {
                    if (it.id == comment.id) {
                        comment.copy(totalLikes = totalLike, isLikedByUser = !it.isLikedByUser)
                    } else {
                        it
                    }
                }
                _uiState.update {
                    it.copy(comments = comments)
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    //need add loading on button like editProfile...
    fun onClickSendComment() {
        val commentText = _uiState.value.commentText
        _uiState.update { it.copy(commentText = "", minorError = "") }
        viewModelScope.launch {
            try {
                val comment = mangeComment.addComment(uiState.value.id, args.postId, commentText)
                _uiState.update {
                    it.copy(
                        commentText = "",
                        post = it.post.copy(totalComments = it.post.totalComments + 1),
                        comments = it.comments + comment.toUIState(uiState.value.id)
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    // need to remove type..
    fun getPostComments(type: Int = 0) {
        viewModelScope.launch {
            try {
                if (!uiState.value.isEndOfPager) {
                    val comments = getPostCommentsUseCase(args.postId, uiState.value.id)
                    if (comments.isNotEmpty()) {
                        _uiState.update {
                            it.copy(
                                comments = (it.comments + comments.toUIState(uiState.value.id)).distinctBy { it.id },
                                isEndOfPager = comments.isEmpty()
                            )
                        }
                    }

                }
            } catch (t: Throwable) {

            }
        }
    }

    fun onClickDeleteComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(minorError = "") }
            try {
                val isDeleted =
                    mangeComment.deleteComment(userId = uiState.value.id, commentId = comment.id)
                if (isDeleted) {
                    _uiState.update {
                        it.copy(
                            comments = it.comments.filterNot { it.id == comment.id },
                            post = it.post.copy(totalComments = (it.post.totalComments - 1))
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }
    //endregion


}