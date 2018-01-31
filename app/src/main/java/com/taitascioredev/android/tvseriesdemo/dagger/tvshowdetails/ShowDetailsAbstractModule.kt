package com.taitascioredev.android.tvseriesdemo.dagger.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.GetSimilarTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.impl.GetSimilarTvShowsUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 26/01/18.
 */
@Module
abstract class ShowDetailsAbstractModule {
    @Binds abstract fun provideGetSimilarTvShowsUseCase(getSimilarTvShowsUseCaseImpl: GetSimilarTvShowsUseCaseImpl): GetSimilarTvShowsUseCase
}