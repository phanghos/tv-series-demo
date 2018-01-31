package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows

import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviIntent

/**
 * Created by rrtatasciore on 30/01/18.
 */
interface SearchTvShowsIntent : MviIntent {

    data class InitialIntent(val query: String) : SearchTvShowsIntent

    data class LoadIntent(val query: String) : SearchTvShowsIntent
}