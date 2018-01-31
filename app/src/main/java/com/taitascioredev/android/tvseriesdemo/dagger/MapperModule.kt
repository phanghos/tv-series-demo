package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.data.entity.mapper.ShowsListMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module
class MapperModule {
    @Provides @Singleton
    fun provideShowsListMapper(): ShowsListMapper {
        return ShowsListMapper()
    }
}