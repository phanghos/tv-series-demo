package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view.adapter.SimilarShowAdapter
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel.ShowDetailsViewModel
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel.ShowDetailsViewModelFactory
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate.ShowDetailsViewState
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseView
import com.taitascioredev.android.tvseriesdemo.util.baseActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_show_details.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 26/01/18.
 */
class ShowDetailsFragment : Fragment(), BaseView<ShowDetailsIntent, ShowDetailsViewState> {

    companion object {

        private val WORD_LIMIT = 30

        fun newInstance(show: MovieDbTvShow): ShowDetailsFragment {
            val f = ShowDetailsFragment()
            val extras = Bundle()
            extras.putSerializable("show", show)
            f.arguments = extras
            return f
        }
    }

    private var isListUpdating = false

    private lateinit var show: MovieDbTvShow

    @Inject lateinit var factory: ShowDetailsViewModelFactory

    private val viewModel: ShowDetailsViewModel by lazy {
        ViewModelProviders.of(this, factory).get(ShowDetailsViewModel::class.java)
    }

    private var adapter = SimilarShowAdapter()

    private val disposables = CompositeDisposable()

    private var loadingDialog: LoadingDialogFragment? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater?.inflate(R.layout.fragment_show_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUiEvents()
        (baseActivity.findViewById(R.id.appbar_layout) as AppBarLayout).setExpanded(true)
        show = arguments.getSerializable("show") as MovieDbTvShow
        baseActivity.supportActionBar?.title = show.name
        renderShow(show)
        list.isNestedScrollingEnabled = true
        list.adapter = adapter
        viewModel.processIntents(intents())
    }

    private fun bindUiEvents() {
        disposables.add(RxView.clicks(tv_show_overview).subscribe { tv_show_overview.text = show.overview })
        disposables.add(adapter.getClickObservable().subscribe { handleClickOnItem(it) })
        disposables.add(viewModel.states().subscribe { render(it) })
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

    private fun initialIntent() = Observable.just(ShowDetailsIntent.InitialIntent.create(show.id!!))

    private fun loadIntent() = RxRecyclerView.scrollEvents(list)
            .filter {
                val layoutManager = list.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                visibleItemCount + pastVisibleItems + 3 >= totalItemCount && !isListUpdating
            }
            .doOnNext { isListUpdating = true }
            .flatMap { Observable.just(ShowDetailsIntent.LoadIntent.create(show.id!!)) }

    private fun retryIntent() = RxView.clicks(btn_retry).map { ShowDetailsIntent.LoadIntent.create(show.id!!) }

    override fun intents(): Observable<ShowDetailsIntent> {
        return Observable.merge(initialIntent(), loadIntent(), retryIntent())
    }

    override fun render(state: ShowDetailsViewState) {
        if (!state.loading()) {
            isListUpdating = false
        }

        when {
            state.loading() -> renderLoading()
            state.similarShows() != null -> {
                if (state.similarShows()!!.isNotEmpty()) renderShows(state.similarShows()!!)
                else renderEmpty()
            }
            state.error() != null -> renderError(state.error()!!)
        }
    }

    private fun renderShow(show: MovieDbTvShow) {
        tv_show_name.text = show.name
        tv_show_overview.text = viewModel.trimContent(show.overview, WORD_LIMIT)
        drawee_view.setImageURI("http://image.tmdb.org/t/p/w342" + show.posterPath)
    }

    private fun renderLoading() {
        btn_retry.visibility = View.GONE
        loadingDialog = LoadingDialogFragment()
        loadingDialog?.show(baseActivity.supportFragmentManager, "")
    }

    private fun renderShows(shows: List<MovieDbTvShow>) {
        btn_retry.visibility = View.GONE
        loadingDialog?.dismiss()
        loadingDialog = null
        list.visibility = View.VISIBLE
        adapter?.add(shows)
    }

    private fun renderEmpty() {
        btn_retry.visibility = View.GONE
        loadingDialog?.dismiss()
        loadingDialog = null
        if (adapter.itemCount == 0) {
            tv_similar_shows.visibility = View.GONE
            Toast.makeText(activity, "No similar shows were found", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "No more similar shows were found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderError(error: Throwable) {
        Snackbar.make(activity.findViewById(R.id.main_content), "Something went wrong", Snackbar.LENGTH_SHORT).show()
        btn_retry.visibility = View.VISIBLE
        loadingDialog?.dismiss()
        loadingDialog = null
        list.visibility = View.GONE
    }

    private fun handleClickOnItem(show: MovieDbTvShow) {
        baseActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowDetailsFragment.newInstance(show)).addToBackStack(null).commit()
    }
}