package com.taitascioredev.android.tvseriesdemo.data.repository

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface MovieRepository {
    fun getPopularTvShows(page: Int): Observable<List<MovieDbTvShow>>
    fun getSimilarTvShows(showId: Int, page: Int): Observable<List<MovieDbTvShow>>
    fun searchTvShows(query: String, page: Int): Observable<List<MovieDbTvShow>>
}