package com.thechance.identity.viewmodel.utils

enum class ErrorMessageType(val value: Int) {
    NO_ERROR(0),
    WRONG_PASSWORD(1),
    NOT_EXIST(2),
    NOT_VALIDATED(3),
    INVALID_USERNAME(4),
    UNKNOWN_ERROR(5)
}