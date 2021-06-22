package com.morse.common.network.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.morse.common.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private var retrofit: Retrofit?=null

    private fun getInstance () : Retrofit {
        if (retrofit == null) {
            var gson = GsonBuilder().disableHtmlEscaping()
            retrofit = Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson.create()))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addCallAdapterFactory(RetryCallAdapterFactory.create())
                    .client(getClientInstance())
                    .build()
        }

        return retrofit!!
    }

    public fun getNetworkInteractor () : Apies {
        return getInstance()?.create(Apies::class.java)
    }

    private fun getClientInstance () : OkHttpClient {

        var loggingInterceptor = HttpLoggingInterceptor()?.apply {
            setLevel( HttpLoggingInterceptor.Level.BODY )
        }
        var okHttp = OkHttpClient.Builder()?.apply {
            this.readTimeout(30 , TimeUnit.SECONDS)
            this.connectTimeout(30 , TimeUnit.SECONDS)
            this.writeTimeout(30, TimeUnit.SECONDS)
            this.addInterceptor(loggingInterceptor)
        }
        return okHttp?.build()
    }


}