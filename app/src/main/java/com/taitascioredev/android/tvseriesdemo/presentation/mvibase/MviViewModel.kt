package com.taitascioredev.android.tvseriesdemo.presentation.mvibase

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface MviViewModel<I : MviIntent> {
    fun processIntents(intents: Observable<I>)
}