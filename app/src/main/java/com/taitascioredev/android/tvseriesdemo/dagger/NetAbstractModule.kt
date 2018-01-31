package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.net.impl.MovieDbServiceImpl
import dagger.Binds
import dagger.Module

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module
abstract class NetAbstractModule {
    @Binds abstract fun provideMovieDbService(movieDbServiceImpl: MovieDbServiceImpl): MovieDbService
}