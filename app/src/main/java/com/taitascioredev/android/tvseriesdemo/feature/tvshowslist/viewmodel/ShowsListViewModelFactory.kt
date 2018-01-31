package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListStateReducer
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListViewModelFactory @Inject constructor(
        private val actionProcessor: ShowsListActionProcessor,
        private val stateReducer: ShowsListStateReducer
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowsListViewModel(actionProcessor, stateReducer) as T
    }
}