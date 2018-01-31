package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows

import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewstate.SearchTvShowsViewState
import com.taitascioredev.android.tvseriesdemo.util.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsStateReducer @Inject constructor() : BiFunction<SearchTvShowsViewState, SearchTvShowsResult, SearchTvShowsViewState> {
    override fun apply(state: SearchTvShowsViewState, result: SearchTvShowsResult): SearchTvShowsViewState {
        return when (result.status) {
            LceStatus.SUCCESS -> SearchTvShowsViewState.success(result.shows!!)
            LceStatus.ERROR -> SearchTvShowsViewState.error(result.error!!)
            LceStatus.IN_FLIGHT -> SearchTvShowsViewState.inFlight()
        }
    }
}