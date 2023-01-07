package com.devfalah.viewmodels

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    suspend fun makeRequest(onSuccess: suspend () -> Unit, onFailure: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (throwable: Throwable) {
            onFailure(throwable)
        }
    }
}