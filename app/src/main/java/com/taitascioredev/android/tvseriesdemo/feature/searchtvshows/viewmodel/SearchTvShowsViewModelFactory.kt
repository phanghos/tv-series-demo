package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsStateReducer
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsViewModelFactory @Inject constructor(
        private val actionProcessor: SearchTvShowsActionProcessor,
        private val stateReducer: SearchTvShowsStateReducer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchTvShowsViewModel(actionProcessor, stateReducer) as T
    }
}