package com.morse.data.remote

import com.morse.domain.*
import kotlinx.coroutines.flow.Flow

interface RemoteGate {

    fun getSeasonAnimeFromRemote(season: String, year: String) : Flow<SeasonAnimeResponse> ?= null

    fun getScheduleAnimeFromRemote () : Flow<ScheduleAnimeResponse> ?= null

    fun getTopAnimeMoviesFromRemote(): Flow<SeasonAnimeResponse> ?= null

    fun getTopAnimeTvShowsFromRemote(): Flow<TopResponse> ?= null

    fun getTopAnimeUpComingFromRemote() : Flow<TopResponse> ?= null

    fun getTopAnimeAiringFromRemote() : Flow<TopResponse> ?= null

    fun getAnimeDetailsFromRemote(animeId : Int) : Flow<AnimeDetailResponse> ?= null

    fun getAnimeCharactersFromRemote(animeId : Int) : Flow<CharactersResponse> ?= null

    fun getAnimeRecommendationsFromRemote(animeId : Int) : Flow<RecommendationResponse> ?= null

    fun getTopMangaFromRemote() : Flow<TopResponse> ?= null

    fun getMangaDetailFromRemote(mangaId : Int) : Flow<MangaDetailsResponse> ?= null

    fun searchOnMangaFromRemote() : Flow<SearchResponse> ?= null

    fun searchOnAnimeFromRemote (animeName : String) : Flow<SearchResponse> ?= null

}