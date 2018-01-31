package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewstate

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviViewState

/**
 * Created by rrtatasciore on 30/01/18.
 */
data class SearchTvShowsViewState(
        val loading: Boolean,
        val shows: List<MovieDbTvShow>?,
        val error: Throwable?
) : MviViewState {
    companion object {
        fun create(loading: Boolean, shows: List<MovieDbTvShow>?, error: Throwable?): SearchTvShowsViewState {
            return SearchTvShowsViewState(loading, shows, error)
        }
        fun idle() = create(false, null, null)
        fun success(shows: List<MovieDbTvShow>) = create(false, shows, null)
        fun error(error: Throwable) = create(false, null, error)
        fun inFlight() = create(true, null, null)
    }
}