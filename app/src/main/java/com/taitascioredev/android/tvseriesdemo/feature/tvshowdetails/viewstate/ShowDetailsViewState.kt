package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate

import com.google.auto.value.AutoValue
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviViewState

/**
 * Created by rrtatasciore on 25/01/18.
 */
@AutoValue
abstract class ShowDetailsViewState : MviViewState {

    abstract fun loading(): Boolean

    abstract fun similarShows(): List<MovieDbTvShow>?

    abstract fun error(): Throwable?

    companion object {
        fun create(loading: Boolean, similarShows: List<MovieDbTvShow>?, error: Throwable?): ShowDetailsViewState {
            return AutoValue_ShowDetailsViewState(loading, similarShows, error)
        }
        fun idle() = create(false, null,null)
        fun success(similarShows: List<MovieDbTvShow>) = create(false, similarShows, null)
        fun error(error: Throwable) = create(false, null, error)
        fun inFlight() = create(true, null, null)
    }
}