package com.morse.ion

import com.morse.data.remote.RemoteGate
import com.morse.domain.*
import kotlinx.coroutines.flow.Flow

class IonExecutor : RemoteGate {

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