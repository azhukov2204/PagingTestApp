package ru.androidlearning.pagingtestapp.di.modules

import org.koin.dsl.module
import ru.androidlearning.pagingtestapp.network.data_source.RedditPaddingDataSource
import ru.androidlearning.pagingtestapp.network.repository.RedditRepository
import ru.androidlearning.pagingtestapp.network.repository.RedditRepositoryImpl

val networkRepositoryModule = module {
    factory<RedditRepository> { RedditRepositoryImpl(redditPaddingDataSource = get()) }
    factory { RedditPaddingDataSource(redditApi = get()) }
}
