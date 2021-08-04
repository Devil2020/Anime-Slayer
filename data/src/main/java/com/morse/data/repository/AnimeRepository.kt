package com.morse.data.repository

import com.morse.domain.*
import kotlinx.coroutines.flow.Flow

class AnimeRepository : IAnimeRepository {

    override fun getAnimeCharacters(): Flow<CharactersResponse>? {
        return super.getAnimeCharacters()
    }

    override fun getAnimeDetails(): Flow<AnimeDetailResponse>? {
        return super.getAnimeDetails()
    }

    override fun getAnimeRecommendations(): Flow<RecommendationResponse>? {
        return super.getAnimeRecommendations()
    }

    override fun getScheduleAnime(): Flow<ScheduleAnimeResponse>? {
        return super.getScheduleAnime()
    }

    override fun getSeasonAnime(): Flow<SeasonAnimeResponse>? {
        return super.getSeasonAnime()
    }

    override fun getTopAnimeAiring(): Flow<TopResponse>? {
        return super.getTopAnimeAiring()
    }

    override fun getTopAnimeMovies(): Flow<SeasonAnimeResponse>? {
        return super.getTopAnimeMovies()
    }

    override fun getTopAnimeTvShows(): Flow<TopResponse>? {
        return super.getTopAnimeTvShows()
    }

    override fun getTopAnimeUpComing(): Flow<TopResponse>? {
        return super.getTopAnimeUpComing()
    }


}