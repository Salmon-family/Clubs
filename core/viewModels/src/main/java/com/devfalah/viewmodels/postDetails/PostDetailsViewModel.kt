package com.devfalah.viewmodels.postDetails

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.SetLikeUseCase
import com.devfalah.usecases.postDetailsUseCases.*
import com.devfalah.viewmodels.postDetails.mapper.toUIState
import com.devfalah.viewmodels.userProfile.PostUIState
import com.devfalah.viewmodels.userProfile.mapper.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase,
    private val getAllCommentsUseCase: GetAllCommentsUseCase,
    private val setCommentUseCase: SetCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val setLikeUseCase: SetLikeUseCase,
    private val setEditCommentUseCase: SetEditCommentUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

//    private val args = PostDetailsArgs(savedStateHandle)

    private val userId = 7
    private val postId = 158

    private val _uiState = MutableStateFlow(PostDetailsUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getPostDetails(userId, postId)
        getAllComments(userId, postId)
        checkCommentDirection(_uiState.value.postDetails.publisherId)
    }

    private fun getPostDetails(userId: Int, postId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                val post = getPostDetailsUseCase(userId, postId)
                _uiState.update {
                    it.copy(postDetails = post.toUIState(), userId = userId, loading = false)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = e.message ?: "") }
            }

        }
    }

    private fun getAllComments(userId: Int, postId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                val comments = getAllCommentsUseCase(userId, postId)
                _uiState.update {
                    it.copy(comments = comments.toUIState(), loading = false)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = e.message ?: "") }
            }

        }
    }

    fun setComment(comment: String/*, file: File*/) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                val newComment = setCommentUseCase(userId, postId, comment, /*file*/)
                _uiState.update {
                    it.copy(
                        comments = it.copy(
                            comments = it.comments.toMutableStateList().apply {
                                add(newComment.toUIState())
                            }
                        ).comments,
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = e.message ?: "") }
            }

        }
    }

    fun onClickDeletedComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                val deleteComment = deleteCommentUseCase(userId, comment.id)
                if (deleteComment) {
                    _uiState.update { it ->
                        it.copy(
                            comments = it.comments.filter { it.id != comment.id },
                            loading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = e.message ?: "") }
            }

        }
    }

    fun sendEditComment(comment: CommentUIState) {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = "") }
            try {
                setEditCommentUseCase(comment.id, comment.content)
                _uiState.update {
                    it.copy(
                        loading = false,
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = e.message ?: "") }
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
                Log.e("Test Test Test", t.message.toString())
                _uiState.update { it.copy(error = t.message.toString()) }
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
                _uiState.update {
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
                Log.e("Test Test Test", t.message.toString())
                _uiState.update { it.copy(error = t.message.toString()) }
            }
        }
    }

    fun onClickEditComment(comment: CommentUIState) {
        viewModelScope.launch {
            val isEdited = !comment.isEdited
            _uiState.update {
                it.copy(comments = _uiState.value.comments.map {
                    if (it.id == comment.id) {
                        it.copy(isEdited = isEdited)
                    } else {
                        it
                    }
                })
            }
        }
    }

    fun checkCommentDirection(publisherId: Int) {
        viewModelScope.launch {
            _uiState.update { it ->
                it.copy(
                    comments = _uiState.value.comments.map {
                        it.copy(isOwner = it.ownerCommentId == publisherId)
                    }
                )
            }
        }
    }

    fun onClickSave(post: PostUIState) {
        Log.e("Test", "Save $post")
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
        setComment(uiState.value.comment/*, File("")*/)
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