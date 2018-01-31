package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel

import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListAction
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import com.taitascioredev.android.tvseriesdemo.presentation.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListViewModel @Inject constructor(
        private val actionProcessor: ShowsListActionProcessor,
        private val stateReducer: ShowsListStateReducer
) : ViewModel(), MviViewModel<ShowsListIntent> {

    private val intentSubject = PublishSubject.create<ShowsListIntent>()

    private val stateObservable: Observable<ShowsListViewState>

    private val intentFilter = ObservableTransformer<ShowsListIntent, ShowsListIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(ShowsListIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is ShowsListIntent.InitialIntent }
            )
        }
    }

    init {
        stateObservable = compose()
    }

    override fun processIntents(intents: Observable<ShowsListIntent>) {
        intents.subscribe(intentSubject)
    }

    private fun compose(): Observable<ShowsListViewState> {
        return intentSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformAction())
                .scan(ShowsListViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }

    private fun actionFromIntent(intent: ShowsListIntent): ShowsListAction {
        return when (intent) {
            is ShowsListIntent.InitialIntent -> ShowsListAction()
            is ShowsListIntent.LoadIntent -> ShowsListAction()
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    fun states() = stateObservable
}