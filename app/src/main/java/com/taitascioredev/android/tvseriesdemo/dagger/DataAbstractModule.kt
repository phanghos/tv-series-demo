package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.data.repository.impl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module
abstract class DataAbstractModule {
    @Binds abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}