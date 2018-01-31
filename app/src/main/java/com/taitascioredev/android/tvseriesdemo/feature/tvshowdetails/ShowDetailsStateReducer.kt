package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate.ShowDetailsViewState
import com.taitascioredev.android.tvseriesdemo.util.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 26/01/18.
 */
class ShowDetailsStateReducer @Inject constructor() : BiFunction<ShowDetailsViewState, ShowDetailsResult, ShowDetailsViewState> {
    override fun apply(state: ShowDetailsViewState, result: ShowDetailsResult): ShowDetailsViewState {
        return when (result.status()) {
            LceStatus.SUCCESS -> ShowDetailsViewState.success(result.similarShows()!!)
            LceStatus.ERROR -> ShowDetailsViewState.error(result.error()!!)
            LceStatus.IN_FLIGHT -> ShowDetailsViewState.inFlight()
        }
    }
}