package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface GetSimilarTvShowsUseCase {
    fun getSimilarTvShows(showId: Int, page: Int): Observable<List<MovieDbTvShow>>
}