package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view.ShowDetailsFragment
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListIntent
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view.adapter.MovieDbShowAdapter
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel.ShowsListViewModel
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel.ShowsListViewModelFactory
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseView
import com.taitascioredev.android.tvseriesdemo.util.baseActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_shows_list.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListFragment : Fragment(), BaseView<ShowsListIntent, ShowsListViewState> {

    companion object {
        private val TAG = ShowsListFragment::class.java.simpleName
    }

    private var isListUpdating = false

    @Inject lateinit var factory: ShowsListViewModelFactory

    private val viewModel: ShowsListViewModel by lazy {
        ViewModelProviders.of(this, factory).get(ShowsListViewModel::class.java)
    }

    private var adapter = MovieDbShowAdapter()

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater?.inflate(R.layout.fragment_shows_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity.supportActionBar?.title = "Popular TV Shows"
        bindUiEvents()
        list.adapter = adapter
        viewModel.processIntents(intents())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun bindUiEvents() {
        disposables.add(RxView.clicks(activity.findViewById(R.id.toolbar)).subscribe { list.smoothScrollToPosition(0) })
        disposables.add(adapter.getClickObservable().subscribe { handleClickOnItem(it) })
        disposables.add(viewModel.states().subscribe { render(it) })
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

    override fun intents(): Observable<ShowsListIntent> {
        return Observable.merge(initialIntent(), loadIntent())
    }

    override fun render(state: ShowsListViewState) {
        if (!state.loading) {
            isListUpdating = false
        }

        when {
            state.loading && !isListUpdating -> renderLoading()
            state.shows != null -> renderShows(state.shows!!)
            state.error != null -> {}
        }
    }

    private fun renderLoading() {
        progress_wheel.visibility = View.VISIBLE
    }

    private fun renderShows(shows: List<MovieDbTvShow>) {
        progress_wheel.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter.add(shows)
    }

    private fun renderError(error: Throwable) {
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
    }

    private fun handleClickOnItem(show: MovieDbTvShow) {
        baseActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowDetailsFragment.newInstance(show)).addToBackStack(null).commit()
    }
}