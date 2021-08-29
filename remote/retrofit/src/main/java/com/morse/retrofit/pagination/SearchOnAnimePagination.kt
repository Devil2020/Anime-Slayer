package com.morse.retrofit.pagination

import com.morse.common.pagination3.BasePagingSource
import com.morse.domain.SearchResponse
import com.morse.domain.TopResponse
import com.morse.retrofit.core.ApiGate
import kotlinx.coroutines.flow.Flow

class SearchOnAnimePagination ( private val api: ApiGate ) : BasePagingSource<SearchResponse>() {

    var searchText : String = ""

    fun injectSearchWord (searchText : String) {
        this.searchText = searchText
    }

    override suspend fun dataSource(position: Int): Flow<SearchResponse> {
        return api.searchAboutAnimeWithName(
            searchText ,
            position
        )
    }
}