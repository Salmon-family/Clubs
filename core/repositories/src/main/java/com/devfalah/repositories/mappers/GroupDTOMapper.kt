package com.devfalah.repositories.mappers

import com.devfalah.repositories.models.group.GroupDTO

fun GroupDTO.toEntity(): Int {
    return guid ?: 0
}

fun List<GroupDTO>.toEntity(): List<Int> = map { it.toEntity() }
