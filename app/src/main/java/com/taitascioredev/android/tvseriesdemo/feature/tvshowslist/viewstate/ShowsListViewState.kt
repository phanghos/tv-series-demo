package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.base.ViewState

/**
 * Created by rrtatasciore on 24/01/18.
 */
data class ShowsListViewState(
        val loading: Boolean,
        val shows: List<MovieDbTvShow>?,
        val error: Throwable?
) : ViewState {

    companion object {
        fun create(loading: Boolean, shows: List<MovieDbTvShow>?, error: Throwable?): ShowsListViewState {
            return ShowsListViewState(loading, shows, error)
        }
        fun idle() = create(false, null, null)
        fun success(shows: List<MovieDbTvShow>) = create(false, shows, null)
        fun error(error: Throwable) = create(false, null, error)
        fun inFlight() = create(true, null, null)
    }
}