package com.taitascioredev.android.tvseriesdemo.presentation.mvibase

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface MviView<I : MviIntent, VS: MviViewState> {

    fun intents(): Observable<I>

    fun render(state: VS)
}