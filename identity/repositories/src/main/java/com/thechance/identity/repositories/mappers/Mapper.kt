package com.thechance.identity.repositories.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}
