package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.google.auto.value.AutoValue
import com.taitascioredev.android.tvseriesdemo.presentation.base.Action

/**
 * Created by rrtatasciore on 25/01/18.
 */
@AutoValue
abstract class ShowDetailsAction : Action {

    abstract fun showId(): Int

    companion object {
        fun create(showId: Int): ShowDetailsAction {
            return AutoValue_ShowDetailsAction(showId)
        }
    }
}