package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.presentation.base.Intent

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface ShowsListIntent : Intent {

    class InitialIntent : ShowsListIntent

    class LoadIntent : ShowsListIntent
}