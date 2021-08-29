package com.morse.common.pagination3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.morse.common.network.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion


/*interface RemotePageModel<Model> {
    ///  @Json(name = "pageIndex")
    var pageIndex: Int

    //  @Json(name = "pageSize")
    var pageSize: Int?

    // @Json(name = "pageData")
    var pageData: List<Model>?

    //  @Json(name = "totalCount")
    var totalCount: Int
}*/
/*abstract class BasePagingSource<M : Any> : PagingSource<Int, M>() {

    companion object {
         var DEFAULT_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, M>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    abstract suspend fun dataSource(position: Int): Flow<ResponseState>


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, M> {
        val position = params.key ?: DEFAULT_PAGE_INDEX
        val response = dataSource(position)
        return response.toLoadResult()
    }

    private suspend fun <Model : Any> Flow<ResponseState>.toLoadResult(): LoadResult<Int, Model> {


        var loadResult: LoadResult<Int, Model> = LoadResult.Page(
            emptyList(), null, null
        )


        collect {

            when (it) {
                is ResponseState.Loading, ResponseState.Idle -> {
                }
                is ResponseState.GenericFailure -> loadResult =
                    LoadResult.Error(throwable = Throwable(it.failure))


                is ResponseState.ServerFailure<*> -> {
                    val errorModel = kotlin.runCatching {
                        //todo
                        *//*  moshiBuilder.build().adapter(UnAuthorizedErrorResponse::class.java)
                              .fromJson(it.failure as? String ?: "")*//*
                    }.getOrNull()
                    loadResult = LoadResult.Error(
                        throwable = Throwable(
                            //todo
                            // errorModel?.message ?: "Unknown Failure"
                            "Unknown Failure"
                        )
                    )
                }
                is ResponseState.Success<*> -> {

                    it as ResponseState.Success<RemotePageModel<Model>?>

                    val pageData = it.data?.pageData ?: emptyList()

                    val position = it.data?.pageIndex ?: DEFAULT_PAGE_INDEX

                    loadResult = LoadResult.Page(
                        pageData,
                        if (position == DEFAULT_PAGE_INDEX) null else position - 1,
                        if (pageData.isEmpty()) {
                            null
                        } else {
                            position + 1
                        }
                    )
                }
            }
        }

        return loadResult

    }
}*/


abstract class BasePagingSource<M : Any> : PagingSource<Int, M>() {

    companion object {
        var DEFAULT_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, M>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    abstract suspend fun dataSource(position: Int): Flow<M>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, M> {
        val position = params.key ?: DEFAULT_PAGE_INDEX
        val response = dataSource(position)
        return response.toLoadResult()
    }

    private suspend fun <Model : Any> Flow<Model>.toLoadResult(): LoadResult<Int, Model> {


        var loadResult: LoadResult<Int, Model> = LoadResult.Page(
            emptyList(), null, null
        )

        this.catch {
            loadResult = LoadResult.Error(throwable = Throwable(it))
        }.onCompletion {
            Log.i(BasePagingSource::class.java.name , "It is Completes the Emition Process .")
        }.collect {
            it as ArrayList<Model>
            val pageData = it ?: emptyList()
            val position = BasePagingSource.DEFAULT_PAGE_INDEX
            loadResult = LoadResult.Page(
                pageData,
                if (position == BasePagingSource.DEFAULT_PAGE_INDEX) null else position - 1,
                if (pageData.isEmpty()) {
                    null
                } else {
                    position + 1
                    BasePagingSource.DEFAULT_PAGE_INDEX ++
                }
            )
        }


        return loadResult

    }

}
