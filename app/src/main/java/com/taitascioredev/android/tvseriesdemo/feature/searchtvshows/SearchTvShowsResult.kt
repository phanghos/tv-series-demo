package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviResult
import com.taitascioredev.android.tvseriesdemo.util.LceStatus

/**
 * Created by rrtatasciore on 30/01/18.
 */
data class SearchTvShowsResult(
        val status: LceStatus,
        val shows: List<MovieDbTvShow>?,
        val error: Throwable?
) : MviResult {
    companion object {
        fun create(status: LceStatus, shows: List<MovieDbTvShow>?, throwable: Throwable?): SearchTvShowsResult {
            return SearchTvShowsResult(status, shows, throwable)
        }
        fun success(shows: List<MovieDbTvShow>) = create(LceStatus.SUCCESS, shows, null)
        fun error(error: Throwable) = create(LceStatus.ERROR, null, error)
        fun inFlight() = create(LceStatus.IN_FLIGHT, null, null)
    }
}