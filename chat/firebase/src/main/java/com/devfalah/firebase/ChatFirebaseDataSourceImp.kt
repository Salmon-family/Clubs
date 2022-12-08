package com.devfalah.firebase

import com.devfalah.repository.ChatFirebaseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatFirebaseDataSourceImp @Inject constructor():ChatFirebaseDataSource {
    override fun onReceiveId(): Flow<Int> {
        return Events.id
    }
}