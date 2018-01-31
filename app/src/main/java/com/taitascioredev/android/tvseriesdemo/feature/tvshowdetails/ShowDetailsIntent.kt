package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.presentation.base.Intent

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface ShowDetailsIntent : Intent {

    class InitialIntent(val showId: Int) : ShowDetailsIntent

    class LoadIntent(val showId: Int) : ShowDetailsIntent
}