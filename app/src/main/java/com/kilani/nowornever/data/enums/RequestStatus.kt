package com.kilani.nowornever.data.enums

enum class RequestStatus {
    SUCCESS,
    SERVER_CONFLICT,
    UNAUTHORIZED,
    NO_INTERNET,
    UNKNOWN_ERROR;

    companion object {
        fun fromHttpCode(httpCode: Int): RequestStatus {
            return when(httpCode) {
                in 200..299 -> SUCCESS
                409 -> SERVER_CONFLICT
                401 -> UNAUTHORIZED
                else -> UNKNOWN_ERROR
            }
        }
    }
}
