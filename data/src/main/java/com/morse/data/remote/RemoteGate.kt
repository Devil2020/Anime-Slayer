package com.morse.data.remote

import androidx.paging.PagingData
import com.morse.domain.*
import kotlinx.coroutines.flow.Flow

interface RemoteGate {

    fun getSeasonAnimeFromRemote(season: String, year: String) : Flow<SeasonAnimeResponse> ?= null

    fun getScheduleAnimeFromRemote () : Flow<ScheduleAnimeResponse> ?= null

    fun getTopAnimeMoviesFromRemote() : Flow<PagingData<TopResponse>> ?= null

    fun getTopAnimeTvShowsFromRemote(): Flow<PagingData<TopResponse>> ?= null

    fun getTopAnimeUpComingFromRemote() : Flow<PagingData<TopResponse>> ?= null

    fun getTopAnimeAiringFromRemote() : Flow<PagingData<TopResponse>> ?= null

    fun getAnimeDetailsFromRemote(animeId : Int) : Flow<AnimeDetailResponse> ?= null

    fun getAnimeCharactersFromRemote(animeId : Int) : Flow<CharactersResponse> ?= null

    fun getAnimeRecommendationsFromRemote(animeId : Int) : Flow<RecommendationResponse> ?= null

    fun getTopMangaFromRemote() : Flow<PagingData<TopResponse>> ?= null

    fun getMangaDetailFromRemote(mangaId : Int) : Flow<MangaDetailsResponse> ?= null

    fun searchOnMangaFromRemote(mangaName : String) : Flow<PagingData<SearchResponse>> ?= null

    fun searchOnAnimeFromRemote (animeName : String) : Flow<PagingData<SearchResponse>> ?= null

}