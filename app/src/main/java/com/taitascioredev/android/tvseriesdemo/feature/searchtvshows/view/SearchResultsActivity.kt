package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.view

import android.app.Activity
import android.app.SearchManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.R.id.*
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.SearchTvShowsIntent
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel.SearchTvShowsViewModel
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewmodel.SearchTvShowsViewModelFactory
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.viewstate.SearchTvShowsViewState
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view.adapter.MovieDbShowAdapter
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseView
import com.taitascioredev.android.tvseriesdemo.presentation.view.BaseActivity
import com.taitascioredev.android.tvseriesdemo.util.enableUpNavigation
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search_results.*
import kotlinx.android.synthetic.main.layout_list.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchResultsActivity : BaseActivity<SearchTvShowsIntent, SearchTvShowsViewState>(), HasActivityInjector {

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    private lateinit var query: String

    @Inject lateinit var factory: SearchTvShowsViewModelFactory

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

    private val viewModel: SearchTvShowsViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SearchTvShowsViewModel::class.java)
    }

    private val adapter = MovieDbShowAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        setSupportActionBar(toolbar)
        enableUpNavigation()
        list.isNestedScrollingEnabled = true
        list.adapter = adapter
        AndroidInjection.inject(this)
        handleNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleNewIntent(intent!!)
    }

    private fun handleNewIntent(intent: Intent) {
        query = intent.getStringExtra(SearchManager.QUERY)
        supportActionBar?.title = "Results for '$query'"
        adapter.clear()
        viewModel.reset()
        viewModel.processIntents(intents())
        bindUiEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.item_search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
        }
    }

    private fun bindUiEvents() {
        addDisposable(viewModel.states().subscribe { render(it) })
    }

    private fun initialIntent(): Observable<SearchTvShowsIntent> = Observable.just(SearchTvShowsIntent.InitialIntent(query))

    override fun intents(): Observable<SearchTvShowsIntent> {
        return initialIntent()
    }

    override fun render(state: SearchTvShowsViewState) {
        when {
            state.loading-> renderLoading()
            state.shows != null -> renderResults(state.shows)
            state.error != null -> renderError(state.error)
        }
    }

    private fun renderLoading() {
        progress_wheel.visibility = View.VISIBLE
    }

    private fun renderResults(results: List<MovieDbTvShow>) {
        progress_wheel.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter.add(results)
    }

    private fun renderError(error: Throwable) {
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
    }
}