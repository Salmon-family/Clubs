package com.devfalah.usecases.posts

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(postId: Int): Post {
        val userId = clubRepository.getUserId()
        val isPostSaved = isSavedInDataBase(postId = postId)
        return  try {
            val post = clubRepository.getPostByID(postId = postId, userID = clubRepository.getUserId())
             post.copy(isSaved = isPostSaved, isMyPost = post.publisherId == userId)
        }catch (t:Throwable){
            if (t.message.toString().contains("NotFound",true)){
                 Post(isFound = false)
            }else{
                throw t
            }
        }

    }

    private suspend fun isSavedInDataBase(postId: Int): Boolean {
        return clubRepository.isPostSavedLocally(postId)
    }

}