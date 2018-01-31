package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import com.taitascioredev.android.tvseriesdemo.util.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListStateReducer @Inject constructor(): BiFunction<ShowsListViewState, ShowsListResult, ShowsListViewState> {
    override fun apply(state: ShowsListViewState, result: ShowsListResult): ShowsListViewState {
        return when (result.status) {
            LceStatus.SUCCESS -> ShowsListViewState.success(result.shows!!)
            LceStatus.ERROR -> ShowsListViewState.error(result.error!!)
            LceStatus.IN_FLIGHT -> ShowsListViewState.inFlight()
        }
    }
}