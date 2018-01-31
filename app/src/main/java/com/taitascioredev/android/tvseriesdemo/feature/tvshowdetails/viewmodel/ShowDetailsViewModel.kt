package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel

import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsAction
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate.ShowDetailsViewState
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 26/01/18.
 */
class ShowDetailsViewModel(
        private val actionProcessor: ShowDetailsActionProcessor,
        private val stateReducer: ShowDetailsStateReducer
) : ViewModel(), BaseViewModel<ShowDetailsIntent> {

    private val intentSubject = PublishSubject.create<ShowDetailsIntent>()

    private val stateObservable: Observable<ShowDetailsViewState>

    private val intentFilter = ObservableTransformer<ShowDetailsIntent, ShowDetailsIntent> { action ->
        action.publish { shared ->
            Observable.merge(
                    shared.ofType(ShowDetailsIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is ShowDetailsIntent.InitialIntent }
            )
        }
    }

    init {
        stateObservable = compose()
    }

    override fun processIntents(intents: Observable<ShowDetailsIntent>) {
        intents.subscribe(intentSubject)
    }

    private fun actionFromIntent(intent: ShowDetailsIntent): ShowDetailsAction {
        return when (intent) {
            is ShowDetailsIntent.InitialIntent -> ShowDetailsAction(intent.showId)
            is ShowDetailsIntent.LoadIntent -> ShowDetailsAction(intent.showId)
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    private fun compose(): Observable<ShowDetailsViewState> {
        return intentSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformAction())
                .scan(ShowDetailsViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }

    fun states() = stateObservable

    fun trimContent(text: String?, limit: Int): String {
        if (text == null) {
            return ""
        }
        val splitIntoWords = text.split(" ")
        return if (splitIntoWords.size < limit) {
            text
        } else {
            val builder = StringBuilder()
            for (i in 0..(limit - 1)) {
                builder.append("${splitIntoWords[i]} ")
            }
            builder.append("(...)")
            builder.toString()
        }
    }
}