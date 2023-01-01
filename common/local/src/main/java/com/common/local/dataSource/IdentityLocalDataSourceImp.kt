package com.common.local.dataSource

import com.common.local.ClubDataBase
import com.thechance.identity.repositories.IdentityLocalDataSource
import javax.inject.Inject

class IdentityLocalDataSourceImp @Inject constructor(
    private val clubDataBase: ClubDataBase,
) : IdentityLocalDataSource {
    override fun clearAllTables() {
        clubDataBase.clearAllTables()
    }
}