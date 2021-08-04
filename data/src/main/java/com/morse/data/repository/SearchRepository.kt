package com.morse.data.repository

import com.morse.domain.ISearchRepository
import com.morse.domain.SearchResponse
import kotlinx.coroutines.flow.Flow

class SearchRepository : ISearchRepository {

    override fun searchOnAnime(): Flow<SearchResponse>? {
        return super.searchOnAnime()
    }

    override fun searchOnManga(): Flow<SearchResponse>? {
        return super.searchOnManga()
    }

}