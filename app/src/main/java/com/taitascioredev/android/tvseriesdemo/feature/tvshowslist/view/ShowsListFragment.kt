package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view.ShowDetailsFragment
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view.adapter.MovieDbShowAdapter
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel.ShowsListViewModel
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel.ShowsListViewModelFactory
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import com.taitascioredev.android.tvseriesdemo.presentation.view.BaseFragment
import com.taitascioredev.android.tvseriesdemo.util.baseActivity
import com.taitascioredev.android.tvseriesdemo.util.snackbar
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.layout_list.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListFragment : BaseFragment<ShowsListIntent, ShowsListViewState>() {

    private var isListUpdating = false

    @Inject lateinit var factory: ShowsListViewModelFactory

    private val viewModel: ShowsListViewModel by lazy {
        ViewModelProviders.of(this, factory).get(ShowsListViewModel::class.java)
    }

    private var adapter = MovieDbShowAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater?.inflate(R.layout.layout_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity.supportActionBar?.title = getString(R.string.popular_shows)
        bindUiEvents()
        list.adapter = adapter
        viewModel.processIntents(intents())
    }

    private fun bindUiEvents() {
        addDisposable(RxView.clicks(activity.findViewById(R.id.toolbar)).subscribe { list.smoothScrollToPosition(0) })
        addDisposable(adapter.getClickObservable().subscribe { handleClickOnItem(it) })
        addDisposable(viewModel.states().subscribe { render(it) })
    }

    private fun initialIntent() = Observable.just(ShowsListIntent.InitialIntent())

    private fun loadIntent() = RxRecyclerView.scrollEvents(list)
            .filter {
                val layoutManager = list.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                visibleItemCount + pastVisibleItems + 3 >= totalItemCount && !isListUpdating
            }
            .doOnNext { isListUpdating = true }
            .flatMap { Observable.just(ShowsListIntent.LoadIntent()) }

    private fun retryIntent() = RxView.clicks(btn_retry).map { ShowsListIntent.LoadIntent() }

    override fun intents(): Observable<ShowsListIntent> {
        return Observable.merge(initialIntent(), loadIntent(), retryIntent())
    }

    override fun render(state: ShowsListViewState) {
        if (!state.loading) {
            isListUpdating = false
        }

        when {
            state.loading && !isListUpdating -> renderLoading()
            state.shows != null -> renderShows(state.shows)
            state.error != null -> renderError(state.error)
        }
    }

    private fun renderLoading() {
        progress_wheel.visibility = View.VISIBLE
        btn_retry.visibility = View.GONE
        tv_error.visibility = View.GONE
    }

    private fun renderShows(shows: List<MovieDbTvShow>) {
        list.visibility = View.VISIBLE
        progress_wheel.visibility = View.GONE
        btn_retry.visibility = View.GONE
        tv_error.visibility = View.GONE
        adapter.add(shows)
    }

    private fun renderError(error: Throwable) {
        if (adapter.itemCount == 0) {
            btn_retry.visibility = View.VISIBLE
            tv_error.visibility = View.VISIBLE
        } else {
            snackbar(activity.findViewById(R.id.main_content), getString(R.string.error))
            tv_error.visibility = View.GONE
        }
        progress_wheel.visibility = View.GONE
    }

    private fun handleClickOnItem(show: MovieDbTvShow) {
        baseActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowDetailsFragment.newInstance(show)).addToBackStack(null).commit()
    }
}