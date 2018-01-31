package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviIntent

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface ShowsListIntent : MviIntent {

    class InitialIntent : ShowsListIntent

    class LoadIntent : ShowsListIntent
}