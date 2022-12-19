package com.devfalah.viewmodels.postDetails

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.SetFavoritePostUseCase
import com.devfalah.usecases.SetLikeUseCase
import com.devfalah.usecases.postDetailsUseCases.*
import com.devfalah.viewmodels.Constants.FIRST_TIME
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
    private val getPostDetailsUseCase: GetPostDetailsUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
    private val setCommentUseCase: SetCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val setLikeUseCase: SetLikeUseCase,
    private val setEditCommentUseCase: SetEditCommentUseCase,
    private val favoritePostUseCase: SetFavoritePostUseCase,
    val getUser: GetUserIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailsUIState())
    val uiState = _uiState.asStateFlow()

    private val args = PostDetailsArgs(savedStateHandle)
    private val userId = args.userId
    private val postId = args.postId

    init {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(userId = getUser()) }
                getData()
            } catch (t: Throwable) {
                _uiState.update { it.copy(majorError = t.message.toString()) }
            }
        }
    }

    private fun getData() {
        getPostDetails(userId, postId)
    }

    private fun getPostDetails(userId: Int, postId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, majorError = "") }
            try {
                val post = getPostDetailsUseCase(userId, postId)
                _uiState.update {
                    it.copy(postDetails = post.toUIState(), userId = userId, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, majorError = e.message.toString()) }
            }

        }
    }

    fun swipeToRefresh(type: Int) {
        viewModelScope.launch {
            try {
                val comments = getAllCommentsUseCase(userId, postId)
                if (comments.isNotEmpty()) {
                    _uiState.update {
                        it.copy(
                            isPagerLoading = false,
                            isLoading = false,
                            comments = it.comments + comments.toUIState()
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isEndOfPager = false,
                            pagerError = "No more comments",
                            isLoading = false,
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isPagerLoading = false,
                        isLoading = false,
                        pagerError = e.message.toString())
                }
            }

        }
    }

    fun setComment(comment: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                val newComment = setCommentUseCase(userId, postId, comment)
                _uiState.update {
                    it.copy(
                        comments = it.copy(
                            comments = it.comments.toMutableStateList().apply {
                                add(newComment.toUIState())
                            }
                        ).comments,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, minorError = e.message.toString()) }
            }

        }
    }

    fun onClickDeletedComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                val deleteComment = deleteCommentUseCase(userId, comment.id)
                if (deleteComment) {
                    _uiState.update { it ->
                        it.copy(
                            comments = it.comments.filter { it.id != comment.id },
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, minorError = e.message.toString()) }
            }

        }
    }

    private fun sendEditComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, minorError = "") }
            try {
                setEditCommentUseCase(comment.id, comment.content)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, minorError = e.message.toString()) }
            }

        }
    }

    fun onClickLike(post: PostUIState) {
        viewModelScope.launch {
            try {
                val totalLikes = setLikeUseCase(
                    postID = post.postId, userId = userId,
                    isLiked = post.isLikedByUser
                )
                val updatedPost = post.copy(
                    isLikedByUser = !post.isLikedByUser, totalLikes = totalLikes
                )
                _uiState.update {
                    it.copy(
                        postDetails = if (it.postDetails.postId == post.postId) updatedPost else it.postDetails
                    )
                }
            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickLikeComment(comment: CommentUIState) {
        viewModelScope.launch {
            try {
                val totalLikes = setLikeUseCase(
                    postID = comment.id, userId = userId,
                    isLiked = comment.isLikedByUser
                )
                val updatedPost = comment.copy(
                    isLikedByUser = !comment.isLikedByUser, totalLikes = totalLikes
                )
                _uiState.update { it ->
                    it.copy(comments = _uiState.value.comments.map {
                        if (it.id == comment.id) {
                            updatedPost
                        } else {
                            it
                        }
                    }
                    )
                }
            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickEditComment(comment: CommentUIState) {
        viewModelScope.launch {
            try {
                val isEdited = !comment.isEdited
                _uiState.update { it ->
                    it.copy(comments = _uiState.value.comments.map {
                        if (it.id == comment.id) {
                            it.copy(isEdited = isEdited)
                        } else {
                            it
                        }
                    })
                }
            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickSave(post: PostUIState) {
        viewModelScope.launch {
            try {
                favoritePostUseCase(post.toEntity())
                val updatedPost = post.copy(isSaved = !post.isSaved)
                _uiState.update {
                    it.copy(
                        postDetails = if (it.postDetails.postId == post.postId) updatedPost else it.postDetails
                    )
                }
            } catch (t: Throwable) {
                Log.e("Test", t.message.toString())
                _uiState.update { it.copy(minorError = t.message.toString()) }
            }
        }
    }

    fun onClickComment(post: PostUIState) {
        Log.e("Test", "Comment $post")
    }

    fun onChanceComment(newValue: String) {
        _uiState.update { it.copy(comment = newValue) }
    }

    fun onChanceCommentEditing(newValue: String) {
        _uiState.update { it ->
            it.copy(
                comments = _uiState.value.comments.map {
                    if (it.isEdited) {
                        it.copy(content = newValue)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun sendComment() {
        setComment(uiState.value.comment)
        _uiState.update { it.copy(comment = "", comments = it.comments) }
    }

    fun sendCommentEdited(comment: CommentUIState) {
        sendEditComment(comment)
        _uiState.update { it.copy(comment = "") }
        closeDialog()
    }

    fun closeDialog() {
        _uiState.update { it ->
            it.copy(
                comments = _uiState.value.comments.map {
                    if (it.isEdited) {
                        it.copy(isEdited = false)
                    } else {
                        it
                    }
                }
            )
        }
    }

}