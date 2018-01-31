package com.taitascioredev.android.tvseriesdemo.presentation.base

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface BaseView<I : Intent, VS: ViewState> {

    fun intents(): Observable<I>

    fun render(state: VS)
}