package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.base.ViewState

/**
 * Created by rrtatasciore on 25/01/18.
 */
data class ShowDetailsViewState(
        val loading: Boolean,
        val similarShows: List<MovieDbTvShow>?,
        val error: Throwable?
) : ViewState {
    companion object {
        fun create(loading: Boolean, similarShows: List<MovieDbTvShow>?, error: Throwable?): ShowDetailsViewState {
            return ShowDetailsViewState(loading, similarShows, error)
        }
        fun idle() = create(false, null,null)
        fun success(similarShows: List<MovieDbTvShow>) = create(false, similarShows, null)
        fun error(error: Throwable) = create(false, null, error)
        fun inFlight() = create(true, null, null)
    }
}