package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 30/01/18.
 */
interface SearchTvShowsUseCase {
    fun searchTvShows(query: String, page: Int): Observable<List<MovieDbTvShow>>
}