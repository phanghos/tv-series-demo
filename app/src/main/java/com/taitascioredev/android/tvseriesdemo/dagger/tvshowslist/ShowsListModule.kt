package com.taitascioredev.android.tvseriesdemo.dagger.tvshowslist

import com.taitascioredev.android.tvseriesdemo.dagger.DataModule
import com.taitascioredev.android.tvseriesdemo.dagger.FragmentScope
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.impl.GetPopularTvShowsUseCaseImpl
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel.ShowsListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module(includes = [ShowsListAbstractModule::class, DataModule::class])
class ShowsListModule {

    @Provides @FragmentScope
    fun provideShowsListViewModelFactory(actionProcessor: ShowsListActionProcessor, stateReducer: ShowsListStateReducer): ShowsListViewModelFactory {
        return ShowsListViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides @FragmentScope
    fun provideShowsListActionProcessor(useCase: GetPopularTvShowsUseCase): ShowsListActionProcessor {
        return ShowsListActionProcessor(useCase)
    }

    @Provides @FragmentScope
    fun provideShowsListStateReducer(): ShowsListStateReducer {
        return ShowsListStateReducer()
    }

    @Provides @FragmentScope
    fun provideGetPopularTvShowsUseCaseImpl(repository: MovieRepository): GetPopularTvShowsUseCaseImpl {
        return GetPopularTvShowsUseCaseImpl(repository)
    }
}