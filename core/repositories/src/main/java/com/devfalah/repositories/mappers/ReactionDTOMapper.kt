package com.devfalah.repositories.mappers

import com.devfalah.repositories.models.ReactionDTO

fun ReactionDTO.toEntity(): Int {
    return count ?: 0
}