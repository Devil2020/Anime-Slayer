package com.morse.domain

import kotlinx.coroutines.flow.Flow

fun main (){



}

interface IAnimeRepository {

    fun getScheduleAnime(): Flow<ScheduleAnimeResponse> ?= null

    fun getSeasonAnime(): Flow<SeasonAnimeResponse> ?= null

    fun getTopAnimeMovies(): Flow<SeasonAnimeResponse> ?= null

    fun getTopAnimeTvShows(): Flow<TopResponse> ?= null

    fun getTopAnimeUpComing() : Flow<TopResponse> ?= null

    fun getTopAnimeAiring() : Flow<TopResponse> ?= null

    fun getAnimeDetails() : Flow<AnimeDetailResponse> ?= null

    fun getAnimeCharacters() : Flow<CharactersResponse> ?= null

    fun getAnimeRecommendations() : Flow<RecommendationResponse> ?= null

}

interface IMangaRepository {

    fun getTopManga() : Flow<TopResponse> ?= null

    fun getMangaDetail() : Flow<MangaDetailsResponse> ?= null

}

interface ISearchRepository {

    fun searchOnManga() : Flow<SearchResponse> ?= null

    fun searchOnAnime() : Flow<SearchResponse> ?= null

}