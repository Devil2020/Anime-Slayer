package com.morse.common.network.common

import com.github.kittinunf.fuel.core.ResponseDeserializable
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

sealed class RequestParams {
    object Empty : RequestParams()
    data class NotEmpty<T : Any>(
        val coroutineContext: CoroutineContext = Dispatchers.IO,
        val listOfParams: List<Pair<String, Any>> = listOf(),
        val serializable: ResponseDeserializable<T>
    ) : RequestParams()
}
