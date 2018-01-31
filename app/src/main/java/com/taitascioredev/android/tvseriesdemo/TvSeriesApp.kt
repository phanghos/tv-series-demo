package com.taitascioredev.android.tvseriesdemo

import com.facebook.drawee.backends.pipeline.Fresco
import com.taitascioredev.android.tvseriesdemo.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by rrtatasciore on 25/01/18.
 */
class TvSeriesApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }
}