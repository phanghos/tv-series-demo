package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.google.auto.value.AutoValue
import com.taitascioredev.android.tvseriesdemo.presentation.base.Intent

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface ShowDetailsIntent : Intent {

    @AutoValue
    abstract class InitialIntent : ShowDetailsIntent {

        abstract fun showId(): Int

        companion object {
            fun create(showId: Int): ShowDetailsIntent {
                return AutoValue_ShowDetailsIntent_InitialIntent(showId)
            }
        }
    }

    @AutoValue
    abstract class LoadIntent : ShowDetailsIntent {

        abstract fun showId(): Int

        companion object {
            fun create(showId: Int): LoadIntent {
                return AutoValue_ShowDetailsIntent_LoadIntent(showId)
            }
        }
    }
}