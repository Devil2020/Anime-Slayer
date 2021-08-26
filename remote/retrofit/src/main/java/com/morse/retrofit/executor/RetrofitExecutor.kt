package com.morse.retrofit.executor

import com.google.gson.GsonBuilder
import com.morse.data.remote.RemoteGate
import com.morse.domain.*
import com.morse.retrofit.BuildConfig
import com.morse.retrofit.core.ApiGate
import com.morse.network_core.flowcalladapter.FlowCallAdapterFactory
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private var retrofit: Retrofit?=null

    private fun getInstance () : Retrofit {
        if (retrofit == null) {
            var gson = GsonBuilder().disableHtmlEscaping()
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(FlowCallAdapterFactory())
               // .addCallAdapterFactory(RetryCallAdapterFactory)
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

class RetrofitExecutor ( private val api : ApiGate) : RemoteGate {

    override fun getSeasonAnimeFromRemote(season: String, year: String): Flow<SeasonAnimeResponse> {
        return api.getSeasonAnime(season = season , year = year )
    }

    override fun getScheduleAnimeFromRemote(): Flow<ScheduleAnimeResponse> {
        return api.getScheduleAnime()
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
        return api.getAnimeDetail(animeId = animeId)
    }

    override fun getAnimeCharactersFromRemote(animeId : Int): Flow<CharactersResponse> {
        return api.getAnimeCharacters( animeId)
    }

    override fun getAnimeRecommendationsFromRemote(animeId : Int): Flow<RecommendationResponse> {
        return api.getAnimeRecommendaion(animeId = animeId)
    }

    override fun getTopMangaFromRemote(): Flow<TopResponse> {
        TODO("Not yet implemented")
    }

    override fun getMangaDetailFromRemote(mangaId : Int): Flow<MangaDetailsResponse> {
        return api.getMangaDetail(mangaId = mangaId)
    }

    override fun searchOnMangaFromRemote(): Flow<SearchResponse> {
        TODO("Not yet implemented")
    }

    override fun searchOnAnimeFromRemote (animeName : String) : Flow<SearchResponse> {
        TODO("Not yet implemented")
    }
}