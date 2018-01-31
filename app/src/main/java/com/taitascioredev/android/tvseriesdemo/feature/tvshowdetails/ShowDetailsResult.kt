package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.util.LceStatus

/**
 * Created by rrtatasciore on 25/01/18.
 */
data class ShowDetailsResult(
        val status: LceStatus,
        val similarShows: List<MovieDbTvShow>?,
        val error: Throwable?
) {
    companion object {
        fun create(status: LceStatus, similarShows: List<MovieDbTvShow>?, error: Throwable?): ShowDetailsResult {
            return ShowDetailsResult(status, similarShows, error)
        }
        fun success(similarShows: List<MovieDbTvShow>) = create(LceStatus.SUCCESS, similarShows, null)
        fun error(error: Throwable) = create(LceStatus.ERROR, null, error)
        fun inFlight() = create(LceStatus.IN_FLIGHT, null, null)
    }
}