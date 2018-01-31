package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.GetSimilarTvShowsUseCase
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowDetailsActionProcessor @Inject constructor(private val useCase: GetSimilarTvShowsUseCase) {

    private var currentPage = 1

    private val list: List<MovieDbTvShow> = ArrayList()

    private fun loadShows(): ObservableTransformer<ShowDetailsAction, ShowDetailsResult> {
        return ObservableTransformer { action ->
            action.map { it }
                    .flatMap {
                        useCase.getSimilarTvShows(it.showId, currentPage)
                                .map {
                                    currentPage++
                                    mergeResults(it)
                                    ShowDetailsResult.success(list)
                                }
                                .onErrorReturn { ShowDetailsResult.error(it) }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .startWith(ShowDetailsResult.inFlight())
            }
        }
    }

    fun transformAction(): ObservableTransformer<ShowDetailsAction, ShowDetailsResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(ShowDetailsAction::class.java).compose(loadShows())
            }
        }
    }

    private fun mergeResults(newList: List<MovieDbTvShow>) {
        newList.forEach { (list as ArrayList).add(it) }
    }
}