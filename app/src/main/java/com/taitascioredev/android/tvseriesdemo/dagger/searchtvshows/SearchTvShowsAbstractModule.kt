package com.taitascioredev.android.tvseriesdemo.dagger.searchtvshows

import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.SearchTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.impl.SearchTvShowsUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 30/01/18.
 */
@Module
abstract class SearchTvShowsAbstractModule {
    @Binds abstract fun provideSearchTvShowsUseCase(searchTvShowsUseCaseImpl: SearchTvShowsUseCaseImpl): SearchTvShowsUseCase
}