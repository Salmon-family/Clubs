package com.thechance.identity.viewmodel.login

import androidx.lifecycle.SavedStateHandle

class LoginPasswordArgs(savedStateHandle: SavedStateHandle){
    val userName: String = checkNotNull(savedStateHandle[USER_NAME_ARG])

    companion object{
        const val USER_NAME_ARG = "userName"
    }
}