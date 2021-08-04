package com.morse.data.repository

import com.morse.domain.IMangaRepository
import com.morse.domain.MangaDetailsResponse
import com.morse.domain.TopResponse
import kotlinx.coroutines.flow.Flow

class MangaRepository : IMangaRepository {

    override fun getMangaDetail(): Flow<MangaDetailsResponse>? {
        return super.getMangaDetail()
    }

    override fun getTopManga(): Flow<TopResponse>? {
        return super.getTopManga()
    }

}