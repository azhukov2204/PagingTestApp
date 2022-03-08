package ru.androidlearning.pagingtestapp.network.repository

import androidx.paging.PagingSource
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo

interface RedditRepository {
    fun getTopPosts(): PagingSource<String, RedditPostInfo>
}
