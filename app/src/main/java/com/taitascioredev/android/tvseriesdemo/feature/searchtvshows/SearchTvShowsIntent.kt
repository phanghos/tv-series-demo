package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows

import com.taitascioredev.android.tvseriesdemo.presentation.base.Intent

/**
 * Created by rrtatasciore on 30/01/18.
 */
interface SearchTvShowsIntent : Intent {

    class InitialIntent(val query: String) : SearchTvShowsIntent

    class LoadIntent(val query: String) : SearchTvShowsIntent
}