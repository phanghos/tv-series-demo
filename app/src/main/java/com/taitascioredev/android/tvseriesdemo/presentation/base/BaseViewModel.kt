package com.taitascioredev.android.tvseriesdemo.presentation.base

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface BaseViewModel<I : Intent> {
    fun processIntents(intents: Observable<I>)
}