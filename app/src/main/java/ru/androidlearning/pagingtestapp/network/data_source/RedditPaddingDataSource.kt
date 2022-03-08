package ru.androidlearning.pagingtestapp.network.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo
import ru.androidlearning.pagingtestapp.network.api.RedditApi
import ru.androidlearning.pagingtestapp.network.response.RedditResponse

class RedditPaddingDataSource(
    private val redditApi: RedditApi
) : PagingSource<String, RedditPostInfo>() {

    override fun getRefreshKey(state: PagingState<String, RedditPostInfo>): String? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPostInfo> {
        try {
            val pageIndex = params.key.orEmpty()
            val pageSize = params.loadSize.coerceAtMost(MAX_PAGE_SIZE_DEFAULT)
            val response = redditApi.getTopPosts(limit = pageSize, after = pageIndex)

            return if (response.isSuccessful) {
                val redditPosts = response.body()
                val nextPageNumber = redditPosts?.data?.after.orEmpty()
                LoadResult.Page(redditPosts?.transform().orEmpty(), null, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun RedditResponse.transform(): List<RedditPostInfo> {
        return this.data.children.map { children ->
            with(children.data) {
                RedditPostInfo(
                    title = title,
                    score = score,
                    numComments = numComments
                )
            }
        }
    }

    companion object {
        private const val MAX_PAGE_SIZE_DEFAULT = 20
    }
}
