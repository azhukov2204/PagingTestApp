package ru.androidlearning.pagingtestapp.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidlearning.pagingtestapp.ui.MainActivity
import ru.androidlearning.pagingtestapp.ui.MainViewModel

val mainViewModelModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(redditRepository = get()) }
    }
}
