package com.taitascioredev.android.tvseriesdemo.dagger.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.dagger.DataModule
import com.taitascioredev.android.tvseriesdemo.dagger.FragmentScope
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.GetSimilarTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.impl.GetSimilarTvShowsUseCaseImpl
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel.ShowDetailsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 26/01/18.
 */
@Module(includes = [ShowDetailsAbstractModule::class, DataModule::class])
class ShowDetailsModule {

    @Provides @FragmentScope
    fun provideShowDetailsViewModelFactory(actionProcessor: ShowDetailsActionProcessor, stateReducer: ShowDetailsStateReducer): ShowDetailsViewModelFactory {
        return ShowDetailsViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides @FragmentScope
    fun provideShowDetailsActionProcessor(useCase: GetSimilarTvShowsUseCase): ShowDetailsActionProcessor {
        return ShowDetailsActionProcessor(useCase)
    }

    @Provides @FragmentScope
    fun provideShowDetailsStateReducer(): ShowDetailsStateReducer {
        return ShowDetailsStateReducer()
    }

    @Provides @FragmentScope
    fun provideGetSimilarTvShowsUseCaseImpl(repository: MovieRepository): GetSimilarTvShowsUseCaseImpl {
        return GetSimilarTvShowsUseCaseImpl(repository)
    }
}