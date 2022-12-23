package com.devfalah.viewmodels.postDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.usecases.GetUserIdUseCase
import com.devfalah.usecases.posts.GetPostCommentsUseCase
import com.devfalah.usecases.posts.GetPostDetailsUseCase
import com.devfalah.usecases.posts.MangeCommentUseCase
import com.devfalah.usecases.posts.SetCommentLikeUseCase
import com.devfalah.viewmodels.postDetails.mapper.toUIState
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

    private fun getPostDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = "") }
            try {
                val post = getPostDetailsUseCase(args.postId, uiState.value.id)
                _uiState.update { it.copy(post = post.toUIState().copy(isSaved = args.isSaved), isLoading = false) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message.toString()) }
            }
        }
    }

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
        _uiState.update { it.copy(commentText = "") }
        viewModelScope.launch {
            try {
                val comment = mangeComment(uiState.value.id, args.postId, commentText)
                _uiState.update {
                    it.copy(
                        commentText = "",
                        post = it.post.copy(totalComments = it.post.totalComments + 1),
                        comments = it.comments + comment.toUIState(uiState.value.id)
                    )
                }
            } catch (t: Throwable) {
                Log.e("Erorrrrrr", t.message.toString())
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
}