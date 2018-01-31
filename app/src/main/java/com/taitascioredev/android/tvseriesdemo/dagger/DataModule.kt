package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.data.entity.mapper.ShowsListMapper
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.net.impl.MovieDbServiceImpl
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.data.repository.impl.MovieRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module(includes = [DataAbstractModule::class])
class DataModule {
    @Provides @FragmentScope
    fun provideMovieRepositoryImpl(service: MovieDbService, mapper: ShowsListMapper): MovieRepositoryImpl {
        return MovieRepositoryImpl(service, mapper)
    }
}