package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class ShowsListActionProcessor @Inject constructor(private val useCase: GetPopularTvShowsUseCase) {

    private var currentPage = 1

    private fun loadShows(): ObservableTransformer<ShowsListAction, ShowsListResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                useCase.getPopularTvShows(currentPage)
                        .map {
                            currentPage++
                            ShowsListResult.success(it)
                        }
                        .onErrorReturn { ShowsListResult.error(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(ShowsListResult.inFlight())
            }
        }
    }

    fun transformAction() = ObservableTransformer<ShowsListAction, ShowsListResult> { action ->
        action.publish { shared ->
            shared.ofType(ShowsListAction::class.java).compose(loadShows())
        }
    }
}