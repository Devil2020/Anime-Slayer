package com.morse.retrofit.pagination

import com.morse.common.pagination3.BasePagingSource
import com.morse.domain.TopResponse
import com.morse.retrofit.core.ApiGate
import kotlinx.coroutines.flow.Flow

class TopMangaMoviesPagination  ( private val api: ApiGate) : BasePagingSource<TopResponse>() {

    override suspend fun dataSource(position: Int): Flow<TopResponse> {
        return api.getTopManga(
            position
        )
    }
}