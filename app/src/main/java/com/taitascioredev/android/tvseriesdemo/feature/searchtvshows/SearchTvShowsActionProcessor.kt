package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.SearchTvShowsUseCase
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsActionProcessor @Inject constructor(private val useCase: SearchTvShowsUseCase) {

    private var page = 1

    private val list: List<MovieDbTvShow> = ArrayList()

    private fun searchTvShows(): ObservableTransformer<SearchTvShowsAction, SearchTvShowsResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                useCase.searchTvShows(it.query, page)
                        .map {
                            page++
                            mergeResults(it)
                            SearchTvShowsResult.success(list)
                        }
                        .onErrorReturn { SearchTvShowsResult.error(it) }
                        .startWith(SearchTvShowsResult.inFlight())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun transformAction(): ObservableTransformer<SearchTvShowsAction, SearchTvShowsResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(SearchTvShowsAction::class.java).compose(searchTvShows())
            }
        }
    }

    private fun mergeResults(newList: List<MovieDbTvShow>) {
        newList.forEach { (list as ArrayList).add(it) }
    }

    fun reset() {
        page = 1
        (list as ArrayList).clear()
    }
}