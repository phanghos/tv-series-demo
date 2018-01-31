package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel

import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsAction
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsIntent
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewstate.SearchTvShowsViewState
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsViewModel @Inject constructor(
        private val actionProcessor: SearchTvShowsActionProcessor,
        private val stateReducer: SearchTvShowsStateReducer
) : ViewModel(), BaseViewModel<SearchTvShowsIntent> {

    private var intentSubject = PublishSubject.create<SearchTvShowsIntent>()

    private var stateObservable: Observable<SearchTvShowsViewState>

    private val intentFilter = ObservableTransformer<SearchTvShowsIntent, SearchTvShowsIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(SearchTvShowsIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is SearchTvShowsIntent.InitialIntent }
            )
        }
    }

    init {
        stateObservable = compose()
    }

    override fun processIntents(intents: Observable<SearchTvShowsIntent>) {
        intents.subscribe(intentSubject)
    }

    private fun actionFromIntent(intent: SearchTvShowsIntent): SearchTvShowsAction {
        return when (intent) {
            is SearchTvShowsIntent.InitialIntent -> SearchTvShowsAction(intent.query)
            is SearchTvShowsIntent.LoadIntent -> SearchTvShowsAction(intent.query)
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    private fun compose(): Observable<SearchTvShowsViewState> {
        return intentSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformAction())
                .scan(SearchTvShowsViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }

    fun states() = stateObservable

    fun reset() {
        actionProcessor.reset()
        intentSubject = PublishSubject.create()
        stateObservable = compose()
    }
}