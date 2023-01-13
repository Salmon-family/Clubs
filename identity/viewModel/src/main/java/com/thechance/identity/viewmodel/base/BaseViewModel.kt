package com.thechance.identity.viewmodel.base

import androidx.lifecycle.ViewModel
import com.thechance.identity.viewmodel.utils.ErrorMessageType

abstract class BaseViewModel : ViewModel() {

    fun getErrorTypeValue(t: Throwable): Int {
        return when (t.message.toString()) {
            "Invalid username or password!" -> ErrorMessageType.WRONG_PASSWORD.value
            "No such a user exists" -> ErrorMessageType.NOT_EXIST.value
            "User not validated" -> ErrorMessageType.NOT_VALIDATED.value
            "username:error" -> ErrorMessageType.INVALID_USERNAME.value
            else -> ErrorMessageType.UNKNOWN_ERROR.value
        }
    }

}