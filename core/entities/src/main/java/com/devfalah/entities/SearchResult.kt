package com.devfalah.entities

data class SearchResult(
    val clubs: List<Club> = emptyList(),
    val isMoreClubs: Boolean = false,
    val users: List<User> = emptyList(),
    val isMoreUsers: Boolean = false
)