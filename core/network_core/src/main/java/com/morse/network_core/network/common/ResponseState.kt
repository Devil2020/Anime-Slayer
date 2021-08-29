package com.morse.common.network.common

sealed class ResponseState {
    object Loading : ResponseState()
    data class Success<out T>(val data: T) : ResponseState()
    data class ServerFailure<out T>(
        val statusCode: Int, val failure: T,
        val exceptionType: ExceptionType
    ) : ResponseState()
    data class GenericFailure(val failure: String, val exceptionType: ExceptionType) :
        ResponseState()
}

