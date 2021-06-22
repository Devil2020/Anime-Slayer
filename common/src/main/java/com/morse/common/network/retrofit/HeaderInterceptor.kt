package com.morse.common.network.retrofit

import com.morse.common.local.preference.shared.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class HeaderInterceptor (private val pref: SharedPreferencesManager) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        try {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${pref.getString("token" ,"")}")
                .addHeader("Accept", "application/json")
                .addHeader("X-localization", pref.getString("lang" ,"Ar") ?: "en")
                .build()
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
        }
        return chain.proceed(request)
    }
}