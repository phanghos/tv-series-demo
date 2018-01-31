package com.taitascioredev.android.tvseriesdemo.dagger.tvshowslist

import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.impl.GetPopularTvShowsUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module
abstract class ShowsListAbstractModule {
    @Binds abstract fun provideGetPopularTvShowsUseCase(getPopularTvShowsUseCaseImpl: GetPopularTvShowsUseCaseImpl): GetPopularTvShowsUseCase
}