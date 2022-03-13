package ru.androidlearning.pagingtestapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.androidlearning.pagingtestapp.domain.RedditPostInfo
import ru.androidlearning.pagingtestapp.network.repository.RedditRepository

class MainViewModel(
    private val redditRepository: RedditRepository
) : ViewModel() {

    val redditTopics: StateFlow<PagingData<RedditPostInfo>> = getPager().flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun getPager(): Pager<String, RedditPostInfo> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)) {
            redditRepository.getTopPosts()
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
