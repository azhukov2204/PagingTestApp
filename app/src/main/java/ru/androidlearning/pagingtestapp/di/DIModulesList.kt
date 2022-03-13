package ru.androidlearning.pagingtestapp.di

import ru.androidlearning.pagingtestapp.di.modules.apiModule
import ru.androidlearning.pagingtestapp.di.modules.mainViewModelModule
import ru.androidlearning.pagingtestapp.di.modules.networkRepositoryModule

val diModules = listOf(
    apiModule,
    networkRepositoryModule,
    mainViewModelModule
)
