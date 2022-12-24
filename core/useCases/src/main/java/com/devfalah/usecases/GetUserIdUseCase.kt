package com.devfalah.usecases

import javax.inject.Inject

class GetUserIdUseCase @Inject constructor() {
    suspend operator fun invoke(): Int {
        return 4
    }
}