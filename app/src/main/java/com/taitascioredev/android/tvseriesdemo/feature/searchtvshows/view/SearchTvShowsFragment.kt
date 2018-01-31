package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsIntent
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel.SearchTvShowsViewModel
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel.SearchTvShowsViewModelFactory
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewstate.SearchTvShowsViewState
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseView
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsFragment : Fragment(), BaseView<SearchTvShowsIntent, SearchTvShowsViewState> {

    @Inject lateinit var factory: SearchTvShowsViewModelFactory

    private val viewModel: SearchTvShowsViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SearchTvShowsViewModel::class.java)
    }

    override fun intents(): Observable<SearchTvShowsIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: SearchTvShowsViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}