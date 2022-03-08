package ru.androidlearning.pagingtestapp.network.repository

import androidx.paging.PagingSource
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo
import ru.androidlearning.pagingtestapp.network.data_source.RedditPaddingDataSource

class RedditRepositoryImpl(
    private val redditPaddingDataSource: RedditPaddingDataSource
) : RedditRepository {
    override fun getTopPosts(): PagingSource<String, RedditPostInfo> {
        return redditPaddingDataSource
    }
}
