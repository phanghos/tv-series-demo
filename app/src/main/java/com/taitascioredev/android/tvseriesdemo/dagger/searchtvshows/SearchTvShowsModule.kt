package com.taitascioredev.android.tvseriesdemo.dagger.searchtvshows

import com.taitascioredev.android.tvseriesdemo.dagger.DataModule
import com.taitascioredev.android.tvseriesdemo.dagger.FragmentScope
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.SearchTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.impl.SearchTvShowsUseCaseImpl
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel.SearchTvShowsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 30/01/18.
 */
@Module(includes = [DataModule::class, SearchTvShowsAbstractModule::class])
class SearchTvShowsModule {

    @Provides @FragmentScope
    fun provideSearchTvShowsViewModelFactory(actionProcessor: SearchTvShowsActionProcessor, stateReducer: SearchTvShowsStateReducer): SearchTvShowsViewModelFactory {
        return SearchTvShowsViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides @FragmentScope
    fun provideSearchTvShowsActionProcessor(useCase: SearchTvShowsUseCase): SearchTvShowsActionProcessor {
        return SearchTvShowsActionProcessor(useCase)
    }

    @Provides @FragmentScope
    fun provideSearchTvShowsStateReducer(): SearchTvShowsStateReducer {
        return SearchTvShowsStateReducer()
    }

    @Provides @FragmentScope
    fun provideSearchTvShowsUseCaseImpl(repository: MovieRepository): SearchTvShowsUseCaseImpl {
        return SearchTvShowsUseCaseImpl(repository)
    }
}