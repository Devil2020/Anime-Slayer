package com.morse.retrofit

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.morse.data.remote.RemoteGate
import com.morse.domain.*
import kotlinx.coroutines.flow.Flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicInteger



object RetrofitBuilder {

    private var retrofit: Retrofit?=null

    private fun getInstance () : Retrofit {
        if (retrofit == null) {
            var gson = GsonBuilder().disableHtmlEscaping()
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(RetryCallAdapterFactory.create())
                .client(getClientInstance())
                .build()
        }

        return retrofit!!
    }

    public fun getNetworkInteractor () : ApiGate {
        return getInstance()?.create(ApiGate::class.java)
    }

    private fun getClientInstance () : OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel( HttpLoggingInterceptor.Level.BODY )
        }
        val okHttp = OkHttpClient.Builder().apply {
            this.readTimeout(30 , TimeUnit.SECONDS)
            this.connectTimeout(30 , TimeUnit.SECONDS)
            this.writeTimeout(30, TimeUnit.SECONDS)
            this.addInterceptor(loggingInterceptor)
        }
        return okHttp.build()
    }

}

class RetrofitExecutor : RemoteGate {

    override fun getSeasonAnimeFromRemote(season: String, year: String): Flow<SeasonAnimeResponse> {
        TODO("Not yet implemented")
    }

    override fun getScheduleAnimeFromRemote(): Flow<ScheduleAnimeResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopAnimeMoviesFromRemote(): Flow<SeasonAnimeResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopAnimeTvShowsFromRemote(): Flow<TopResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopAnimeUpComingFromRemote(): Flow<TopResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopAnimeAiringFromRemote(): Flow<TopResponse> {
        TODO("Not yet implemented")
    }

    override fun getAnimeDetailsFromRemote(animeId : Int): Flow<AnimeDetailResponse> {
        TODO("Not yet implemented")
    }

    override fun getAnimeCharactersFromRemote(animeId : Int): Flow<CharactersResponse> {
        TODO("Not yet implemented")
    }

    override fun getAnimeRecommendationsFromRemote(animeId : Int): Flow<RecommendationResponse> {
        TODO("Not yet implemented")
    }

    override fun getTopMangaFromRemote(): Flow<TopResponse> {
        TODO("Not yet implemented")
    }

    override fun getMangaDetailFromRemote(mangaId : Int): Flow<MangaDetailsResponse> {
        TODO("Not yet implemented")
    }

    override fun searchOnMangaFromRemote(): Flow<SearchResponse> {
        TODO("Not yet implemented")
    }

    override fun searchOnAnimeFromRemote (animeName : String) : Flow<SearchResponse> {
        TODO("Not yet implemented")
    }
}