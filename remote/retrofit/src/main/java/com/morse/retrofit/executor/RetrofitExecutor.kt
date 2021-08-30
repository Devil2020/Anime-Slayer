package com.morse.retrofit.executor

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.GsonBuilder
import com.morse.data.remote.RemoteGate
import com.morse.domain.*
import com.morse.network_core.flowcalladapter.FlowCallAdapterFactory
import com.morse.retrofit.BuildConfig
import com.morse.retrofit.core.ApiGate
import com.morse.retrofit.pagination.SearchOnAnimePagination
import com.morse.retrofit.pagination.SearchOnMangaPagination
import com.morse.retrofit.pagination.TopAnimeMoviesPagination
import com.morse.retrofit.pagination.TopMangaMoviesPagination
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (retrofit == null) {
            var gson = GsonBuilder().disableHtmlEscaping()
            retrofit = Retrofit.Builder()
                .baseUrl(com.morse.network_core.BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(FlowCallAdapterFactory())
                // .addCallAdapterFactory(RetryCallAdapterFactory)
                .client(getClientInstance())
                .build()
        }

        return retrofit!!
    }

    public fun getNetworkInteractor(): ApiGate {
        return getInstance()?.create(ApiGate::class.java)
    }

    private fun getClientInstance(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttp = OkHttpClient.Builder().apply {
            this.readTimeout(30, TimeUnit.SECONDS)
            this.connectTimeout(30, TimeUnit.SECONDS)
            this.writeTimeout(30, TimeUnit.SECONDS)
            this.addInterceptor(loggingInterceptor)
        }
        return okHttp.build()
    }

}

const val PAGINATION_SIZE = 10

class RetrofitExecutor(
    private val api: ApiGate,
    private val topAnimeMoviesPagination: TopAnimeMoviesPagination,
    private val topAnimeTVPagination: TopAnimeMoviesPagination,
    private val topAnimeAiringPagination: TopAnimeMoviesPagination,
    private val topAnimeUpComingPagination: TopAnimeMoviesPagination,
    private val topMangaPagination: TopMangaMoviesPagination ,
    private val searchOnAnimePagination: SearchOnAnimePagination,
    private val searchOnMangaPagination: SearchOnMangaPagination
) : RemoteGate {

    override fun getSeasonAnimeFromRemote(season: String, year: String): Flow<SeasonAnimeResponse> {
        return api.getSeasonAnime(season = season, year = year)
    }

    override fun getScheduleAnimeFromRemote(): Flow<ScheduleAnimeResponse> {
        return api.getScheduleAnime()
    }

    override fun getTopAnimeMoviesFromRemote(): Flow<PagingData<TopResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { topAnimeMoviesPagination }
        ).flow
    }

    override fun getTopAnimeTvShowsFromRemote(): Flow<PagingData<TopResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { topAnimeTVPagination }
        ).flow
    }

    override fun getTopAnimeUpComingFromRemote(): Flow<PagingData<TopResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { topAnimeUpComingPagination }
        ).flow
    }

    override fun getTopAnimeAiringFromRemote(): Flow<PagingData<TopResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { topAnimeAiringPagination }
        ).flow
    }

    override fun getAnimeDetailsFromRemote(animeId: Int): Flow<AnimeDetailResponse> {
        return api.getAnimeDetail(animeId = animeId)
    }

    override fun getAnimeCharactersFromRemote(animeId: Int): Flow<CharactersResponse> {
        return api.getAnimeCharacters(animeId)
    }

    override fun getAnimeRecommendationsFromRemote(animeId: Int): Flow<RecommendationResponse> {
        return api.getAnimeRecommendaion(animeId = animeId)
    }

    override fun getTopMangaFromRemote(): Flow<PagingData<TopResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { topMangaPagination }
        ).flow
    }

    override fun getMangaDetailFromRemote(mangaId: Int): Flow<MangaDetailsResponse> {
        return api.getMangaDetail(mangaId = mangaId)
    }

    override fun searchOnMangaFromRemote(mangaName: String): Flow<PagingData<SearchResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { searchOnMangaPagination }
        ).flow
    }

    override fun searchOnAnimeFromRemote(animeName: String): Flow<PagingData<SearchResponse>> {
        return Pager(
            config = PagingConfig(PAGINATION_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { searchOnAnimePagination }
        ).flow
    }
}