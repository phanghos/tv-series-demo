package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbApi
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.net.impl.MovieDbServiceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module(includes = [NetAbstractModule::class])
class NetModule {

    @Provides @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides @Singleton
    fun provideMovieDbApi(retrofit: Retrofit): MovieDbApi {
        return retrofit.create(MovieDbApi::class.java)
    }

    @Provides @Singleton
    fun provideMovieDbServiceImpl(api: MovieDbApi): MovieDbServiceImpl {
        return MovieDbServiceImpl(api)
    }
}