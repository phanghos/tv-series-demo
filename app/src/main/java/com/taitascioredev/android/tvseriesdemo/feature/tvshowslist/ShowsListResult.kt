package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.base.Result
import com.taitascioredev.android.tvseriesdemo.util.LceStatus

/**
 * Created by rrtatasciore on 24/01/18.
 */
data class ShowsListResult(
        val status: LceStatus,
        val shows: List<MovieDbTvShow>?,
        val error: Throwable?
) : Result {
    companion object {
        fun create(status: LceStatus, shows: List<MovieDbTvShow>?, error: Throwable?): ShowsListResult {
            return ShowsListResult(status, shows, error)
        }
        fun success(shows: List<MovieDbTvShow>) = create(LceStatus.SUCCESS, shows, null)
        fun error(error: Throwable) = create(LceStatus.ERROR, null, error)
        fun inFlight() = create(LceStatus.IN_FLIGHT, null, null)
    }
}