package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface GetPopularTvShowsUseCase {
    fun getPopularTvShows(page: Int): Observable<List<MovieDbTvShow>>
}