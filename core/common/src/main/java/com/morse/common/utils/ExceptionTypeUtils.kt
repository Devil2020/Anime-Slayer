package com.morse.common.utils

import com.morse.common.network.common.ExceptionType
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionTypeUtils {
    /*
    * What is input Comes ? it will be the throwable , okay ?
    * Result ? it will be a custom data class contains imageResource and Title , body and Lamda Function for Retry
    */


    fun getResponseStateExceptionType(e: Throwable): ExceptionType {
        return when (e) {
            is SocketTimeoutException -> return ExceptionType.SocketTimeoutException
            is javax.net.ssl.SSLHandshakeException -> return ExceptionType.SSLHandshakeException
            is UnknownHostException -> return ExceptionType.UnknownHostException
            is java.net.ProtocolException -> return ExceptionType.ProtocolException
            is javax.net.ssl.SSLException -> return ExceptionType.SSLException
            is java.net.SocketException -> return ExceptionType.SocketException
            is java.io.EOFException -> return ExceptionType.EOFException
            is java.util.concurrent.CancellationException -> return ExceptionType.UserCancellationException
            else -> return ExceptionType.GenericException
        }
    }

    fun getResponseStateExceptionType(statusCode: Int): ExceptionType {
        return when (statusCode) {
            404 -> return ExceptionType.HttpException_404
            422 -> return ExceptionType.HttpException_422
            403 -> return ExceptionType.HttpException_403
            504 -> return ExceptionType.HttpException_504
            401 -> return ExceptionType.HttpException_401
            400 -> return ExceptionType.HttpException_400
            else -> return ExceptionType.HttpException_Generic
        }
    }
}