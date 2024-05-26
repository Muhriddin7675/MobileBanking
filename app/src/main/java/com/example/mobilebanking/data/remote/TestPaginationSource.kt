package com.example.mobilebanking.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobilebanking.data.remote.api.TransferApi
import com.example.mobilebanking.data.remote.response.Child

class TestPaginationSource(private val api: TransferApi) : PagingSource<Int, Child>() {

    override fun getRefreshKey(state: PagingState<Int, Child>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Child> {
        return try {
            val page = params.key ?: 1
            val response = api.getHistory(10, page)
            if (response.isSuccessful && response.body() != null) {
                LoadResult.Page(
                    response.body()!!.child,
                    if (page > 1) page.minus(1) else null,
                    if (response.body()!!.totalPages > page) page.plus(1) else null
                )
            } else {
                LoadResult.Error(
                    Exception("Unknown Error!!")
                )
            }
        }catch (e : Exception){
            LoadResult.Error(
                Exception("Unknown Error!!")
            )
        }
    }

}


/*class RepostitoryImpl @Inject constructor(private val api: TestApi) {

    fun getHistory(size: Int, pageCount: Int): Flow<PagingData<ApiResponse>> =
        Pager(
            config = PagingConfig(size), pagingSourceFactory = { TestPaginationSource(api = api) },
        ).flow
}

class ViewModelImpl @Inject constructor(private val impl: RepostitoryImpl) : ViewModel() {
    fun getHistory(size, pageCount: Int): Flow<PagingData<ApiResponse>> =
        impl.getHistory(size = size, pageCount = pageCount).cachedIn(viewModelScope)
}*/


data class ApiResponse(
    val name: String,
    val time: String,
    val location: String
)