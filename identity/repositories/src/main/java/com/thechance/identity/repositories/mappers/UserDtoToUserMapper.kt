package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.Account
import com.thechance.identity.repositories.models.AccountDTO

fun AccountDTO.toEntity(): Account {
    return Account(
        fullName = this.fullName ?: "",
        username = this.username ?: "",
        guid = this.guid ?: 0,
    )
}