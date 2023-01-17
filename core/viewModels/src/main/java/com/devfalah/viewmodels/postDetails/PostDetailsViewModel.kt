package com.devfalah.viewmodels.postDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.posts.*
import com.devfalah.usecases.user.GetUserAccountDetailsUseCase
import com.devfalah.viewmodels.postDetails.mapper.toUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toEntity
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import com.devfalah.viewmodels.util.Constants.MAX_PAGE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    val getPostCommentsUseCase: GetPostCommentsUseCase,
    val getPostDetailsUseCase: GetPostDetailsUseCase,
    val manageComment: ManageCommentUseCase,
    val commentLike: SetCommentLikeUseCase,
    val favoritePostUseCase: SetFavoritePostUseCase,
    val postLike: SetPostLikeUseCase,
    val deletePostUseCase: DeletePostUseCase,
    val publisherDetails: GetUserAccountDetailsUseCase,
    val updatePost: UpdatePostUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailsUIState())
    val uiState = _uiState.asStateFlow()
    private val args = PostDetailsArgs(savedStateHandle)

    private var likeJob: Job? = null

    init {
        getData()
    }

    fun getData() {
        getPostDetails(args.postId)
    }

    //region Post
    private fun getPublisherDetails(publisherId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(minorError = "") }
            try {
                val user = publisherDetails(publisherId)
                _uiState.update {
                    it.copy(
                        post = it.post.copy(
                            publisherId = publisherId,
                            publisherName = user.name,
                            publisherImage = user.profileUrl,
                        )
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    private fun getPostDetails(postId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val post = getPostDetailsUseCase(postId)
                _uiState.update { it.copy(post = post.toUIState(), isLoading = false) }
                if (uiState.value.post.isFound) {
                    getPublisherDetails(post.publisherId)
                    getPostComments()
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

    fun onClickLikePost(post: PostUIState) {
        likeJob?.cancel()
        likeJob = viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        error = "",
                        post = post.copy(
                            isLikedByUser = !post.isLikedByUser,
                            totalLikes = if (post.isLikedByUser) post.totalLikes - 1 else post.totalLikes + 1
                        )
                    )
                }
                delay(1000)
                postLike(args.postId, post.isLikedByUser, post.publisherId, post.groupName)
            } catch (t: Throwable) {
                //_uiState.update { it.copy(error = t.message.toString()) }
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
                if (deletePostUseCase(post.postId)) {
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
                    commentId = comment.id, isLiked = comment.isLikedByUser
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
        val commentText = _uiState.value.commentText.trim()
        _uiState.update { it.copy(commentText = "", minorError = "") }
        viewModelScope.launch {
            try {
                val comment = manageComment.addComment(
                    args.postId,
                    commentText,
                    _uiState.value.post.publisherId,
                    _uiState.value.post.groupName
                )
                _uiState.update {
                    it.copy(
                        commentText = "",
                        post = it.post.copy(totalComments = it.post.totalComments + 1),
                        comments = it.comments + comment.toUIState()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun getPostComments() {
        _uiState.update { it.copy(isPagerLoading = true, minorError = "", error = "") }
        viewModelScope.launch {
            try {
                if (!uiState.value.isEndOfPager) {
                    val comments = getPostCommentsUseCase(args.postId)
                    _uiState.update {
                        it.copy(
                            comments = (it.comments + comments.toUIState().distinctBy { it.id }),
                            isEndOfPager = (comments.isEmpty() || comments.size < MAX_PAGE_ITEM),
                            isPagerLoading = false,
                            isLoading = false
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false, minorError = t.message.toString()
                    )
                }
            }
        }
    }

    fun onClickDeleteComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(minorError = "") }
            try {
                val isDeleted = manageComment.deleteComment(commentId = comment.id)
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

    fun updateLocalPost() {
        viewModelScope.launch {
            updatePost(uiState.value.post.toEntity())
        }
    }
}