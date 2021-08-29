package com.morse.fuel

import androidx.paging.PagingData
import com.morse.data.remote.RemoteGate
import com.morse.domain.*
import kotlinx.coroutines.flow.Flow

class FuelExecutor : RemoteGate {
    override fun searchOnMangaFromRemote(mangaName: String): Flow<PagingData<SearchResponse>>? {
        return super.searchOnMangaFromRemote(mangaName)
    }

    override fun getAnimeCharactersFromRemote(animeId: Int): Flow<CharactersResponse>? {
        return super.getAnimeCharactersFromRemote(animeId)
    }

    override fun getAnimeDetailsFromRemote(animeId: Int): Flow<AnimeDetailResponse>? {
        return super.getAnimeDetailsFromRemote(animeId)
    }

    override fun searchOnAnimeFromRemote(animeName: String): Flow<PagingData<SearchResponse>>? {
        return super.searchOnAnimeFromRemote(animeName)
    }

    override fun getTopMangaFromRemote(): Flow<PagingData<TopResponse>>? {
        return super.getTopMangaFromRemote()
    }

    override fun getTopAnimeUpComingFromRemote(): Flow<PagingData<TopResponse>>? {
        return super.getTopAnimeUpComingFromRemote()
    }

    override fun getTopAnimeTvShowsFromRemote(): Flow<PagingData<TopResponse>>? {
        return super.getTopAnimeTvShowsFromRemote()
    }

    override fun getTopAnimeMoviesFromRemote(): Flow<PagingData<TopResponse>>? {
        return super.getTopAnimeMoviesFromRemote()
    }

    override fun getTopAnimeAiringFromRemote(): Flow<PagingData<TopResponse>>? {
        return super.getTopAnimeAiringFromRemote()
    }

    override fun getSeasonAnimeFromRemote(
        season: String,
        year: String
    ): Flow<SeasonAnimeResponse>? {
        return super.getSeasonAnimeFromRemote(season, year)
    }

    override fun getScheduleAnimeFromRemote(): Flow<ScheduleAnimeResponse>? {
        return super.getScheduleAnimeFromRemote()
    }

    override fun getMangaDetailFromRemote(mangaId: Int): Flow<MangaDetailsResponse>? {
        return super.getMangaDetailFromRemote(mangaId)
    }

    override fun getAnimeRecommendationsFromRemote(animeId: Int): Flow<RecommendationResponse>? {
        return super.getAnimeRecommendationsFromRemote(animeId)
    }


}