package com.thechance.identity.usecases

import javax.inject.Inject

class ClearDataBaseUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
) {
    suspend operator fun invoke(newUserId: Int) {
        val oldUserId = identityRepository.getUserId()
        if (oldUserId != newUserId) {
            identityRepository.clearTables()
        }
    }
}