package com.devfalah.usecases.posts

import com.devfalah.entities.Post
import com.devfalah.usecases.repository.ClubRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
) {
    suspend operator fun invoke(postId: Int, userID: Int): Post {
        val post = clubRepository.getPostByID(postId = postId, userID = userID)
        println("Testttttt $post")
        return post
    }


}