package com.morse.common.network.flowconverter

import android.util.Log
import com.morse.common.network.common.ResponseState
import com.morse.common.network.isNetworkConnected
import com.morse.common.utils.ExceptionTypeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

class ApiCallHandler constructor() {

    fun executeApiCall(
        apiCall: suspend () -> Any?,
    ): Flow<ResponseState> {
        return flow {
            emit(ResponseState.Loading)
            val response = apiCall.invoke() as Response<Any?>
            if (response.isSuccessful) {
                emit(ResponseState.Success(response.body()))
            } else {
                emit(
                    ResponseState.ServerFailure(
                        response.code(),
                        response.errorBody()?.string(),
                        ExceptionTypeUtils.getResponseStateExceptionType(response.code())
                    )
                )
            }
        }.catch { exception: Throwable ->
            exception.message?.let {
                Log.e("ApiCallHandler", it)
            }
            val isNetworkConnected = isNetworkConnected()
            val errorMsg = when (exception) {
                is IOException -> {
                    if (isNetworkConnected) {
                        "Server is unreachable"
                    } else {
                        "No Internet connection"
                    }
                }

                else -> {
                    exception.message ?: "Generic Error"
                }
            }
            emit(
                ResponseState.GenericFailure(
                    errorMsg,
                    ExceptionTypeUtils.getResponseStateExceptionType(exception)
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}