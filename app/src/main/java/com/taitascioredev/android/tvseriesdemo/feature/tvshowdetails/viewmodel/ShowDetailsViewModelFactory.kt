package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsStateReducer
import javax.inject.Inject

/**
 * Created by rrtatasciore on 26/01/18.
 */
class ShowDetailsViewModelFactory @Inject constructor(
        private val actionProcessor: ShowDetailsActionProcessor,
        private val stateReducer: ShowDetailsStateReducer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowDetailsViewModel(actionProcessor, stateReducer) as T
    }
}