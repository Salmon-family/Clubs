package com.devfalah.repositories.mappers

import com.devfalah.repositories.models.ReactionDTO

internal fun ReactionDTO.toEntity(): Int {
    return count ?: 0
}