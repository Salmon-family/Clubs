package com.devfalah.repository

import kotlinx.coroutines.flow.Flow

interface ChatFirebaseDataSource {
     fun onReceiveId():Flow<Int>
}