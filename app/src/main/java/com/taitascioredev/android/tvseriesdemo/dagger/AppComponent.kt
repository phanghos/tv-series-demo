package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.TvSeriesApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetModule::class, MapperModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<TvSeriesApp>