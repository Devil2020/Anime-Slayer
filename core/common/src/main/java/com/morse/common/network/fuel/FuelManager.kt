package com.morse.common.network

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.core.interceptors.loggingRequestInterceptor
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.morse.common.BuildConfig
import com.morse.common.network.common.RequestParams
import com.morse.common.network.common.RequestType
import com.morse.common.network.common.ResponseState
import com.morse.common.utils.ExceptionTypeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import com.github.kittinunf.fuel.core.FuelManager as NetworkFuelManager

class FuelExecutor private constructor(private val fuelManager: NetworkFuelManager) {

    companion object {
        private val LOCK = Any()

        @Volatile
        private var instanceOfFuelManager: FuelExecutor? = null
        fun builder(): FuelExecutor {
            return instanceOfFuelManager ?: synchronized(LOCK) {
                NetworkFuelManager.instance.basePath = BuildConfig.SERVER_URL
                NetworkFuelManager.instance.addRequestInterceptor(loggingRequestInterceptor())
                FuelExecutor(NetworkFuelManager.instance).apply {
                    instanceOfFuelManager = this
                }
            }
        }
    }

    fun addHeader(headerName: String, headerValue: String) {
        fuelManager.baseHeaders = mapOf(headerName to headerValue)
    }

    fun removeHeader(headerName: String) {
        fuelManager.baseHeaders = mapOf()
        fuelManager.baseHeaders = fuelManager.baseHeaders?.minus(headerName)
    }

    fun <T : Any> executeApiQuery(
        url: String,
        requestType: RequestType? = RequestType.GET,
        requestParams: RequestParams? = RequestParams.Empty
    ): Flow<ResponseState> {
        when (requestType) {
            RequestType.GET -> {
                return executeGetApiQueryCoroutine<T>(url, requestParams)
            }
            RequestType.POST -> {
                return executePostApiQueryCoroutine<T>(url, requestParams)
            }
            RequestType.PUT -> {
                return executeUpdateApiQueryCoroutine<T>(url, requestParams)
            }
            RequestType.DELET -> {
                return executeDeleteApiQueryCoroutine<T>(url, requestParams)
            }
            else -> throw NoSuchFieldException("Not Found Request Type")
        }
        return emptyFlow()
    }

    private fun <T : Any> executeGetApiQueryCoroutine(
        url: String,
        requestParams: RequestParams? = null
    ) = flow {
        try {
            this.emit(ResponseState.Loading)
            fuelManager.get(
                url,
                parameters = if (requestParams is RequestParams.NotEmpty<*>) requestParams.listOfParams else null
            ).awaitObjectResponseResult(
                (requestParams as RequestParams.NotEmpty<T>).serializable
            ).third.fold(
                { data -> this.emit(ResponseState.Success(data)) },
                { error ->
                    this.emit(
                        ResponseState.ServerFailure(
                            error.response.statusCode,
                            error.exception,
                            ExceptionTypeUtils.getResponseStateExceptionType(error.exception)
                        )
                    )
                }
            )
        } catch (e: Exception) {
            this.emit(
                ResponseState.GenericFailure(
                    e.localizedMessage,
                    ExceptionTypeUtils.getResponseStateExceptionType(e)
                )
            )
        }
    }

    private fun <T : Any> executePostApiQueryCoroutine(
        url: String,
        requestParams: RequestParams? = null
    ) = flow {
        try {
            this.emit(ResponseState.Loading)
            fuelManager.post(
                url,
                parameters = if (requestParams is RequestParams.NotEmpty<*>) requestParams.listOfParams else null
            ).awaitObjectResponseResult(
                (requestParams as RequestParams.NotEmpty<T>).serializable
            ).third.fold(
                { data -> this.emit(ResponseState.Success(data)) },
                { error ->
                    this.emit(
                        ResponseState.ServerFailure(
                            error.response.statusCode,
                            error.exception,
                            ExceptionTypeUtils.getResponseStateExceptionType(error.exception)
                        )
                    )
                }
            )
        } catch (e: Exception) {
            this.emit(
                ResponseState.GenericFailure(
                    e.localizedMessage,
                    ExceptionTypeUtils.getResponseStateExceptionType(e)
                )
            )
        }
    }

    private fun <T : Any> executeUpdateApiQueryCoroutine(
        url: String,
        requestParams: RequestParams? = null
    ) = flow {
        try {
            this.emit(ResponseState.Loading)
            fuelManager.put(
                url,
                parameters = if (requestParams is RequestParams.NotEmpty<*>) requestParams.listOfParams else null
            ).awaitObjectResponseResult(
                (requestParams as RequestParams.NotEmpty<T>).serializable
            ).third.fold(
                { data -> this.emit(ResponseState.Success<T>(data)) },
                { error ->
                    this.emit(
                        ResponseState.ServerFailure(
                            error.response.statusCode,
                            error.exception,
                            ExceptionTypeUtils.getResponseStateExceptionType(error.exception)
                        )
                    )
                }
            )
        } catch (e: Exception) {
            this.emit(
                ResponseState.GenericFailure(
                    e.localizedMessage,
                    ExceptionTypeUtils.getResponseStateExceptionType(e)
                )
            )
        }
    }

    private fun <T : Any> executeDeleteApiQueryCoroutine(
        url: String,
        requestParams: RequestParams? = null
    ) = flow {
        try {
            this.emit(ResponseState.Loading)
            fuelManager.delete(
                url,
                parameters = if (requestParams is RequestParams.NotEmpty<*>) requestParams.listOfParams else null
            ).awaitObjectResponseResult(
                (requestParams as RequestParams.NotEmpty<T>).serializable
            ).third.fold(
                { data -> this.emit(ResponseState.Success(data)) },
                { error ->
                    this.emit(
                        ResponseState.ServerFailure(
                            error.response.statusCode,
                            error.exception,
                            ExceptionTypeUtils.getResponseStateExceptionType(error.exception)
                        )
                    )
                }
            )
        } catch (e: Exception) {
            this.emit(
                ResponseState.GenericFailure(
                    e.localizedMessage,
                    ExceptionTypeUtils.getResponseStateExceptionType(e)
                )
            )
        }
    }

}

data class MovieResponse(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("results")
    val results: List<Result>? = arrayListOf(),
    @SerializedName("total_pages")
    val total_pages: Int? = 0,
    @SerializedName("total_results")
    val total_results: Int? = 0
)

data class Result(
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdrop_path: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("original_language")
    val original_language: String? = "",
    @SerializedName("original_title")
    val original_title: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @SerializedName("poster_path")
    val poster_path: String? = "",
    @SerializedName("release_date")
    val release_date: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val vote_average: Double? = 0.0,
    @SerializedName("vote_count")
    val vote_count: Int? = 0
)

class MovieResponseDeserializer : ResponseDeserializable<MovieResponse> {

    override fun deserialize(content: String) =
        Gson().fromJson(content, MovieResponse::class.java)
}