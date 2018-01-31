package com.taitascioredev.android.tvseriesdemo.data.net

import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 25/01/18.
 */
interface MovieDbService {
    fun getPopularTvShows(page: Int): Observable<ShowsListResponseEntity>
    fun getSimilarTvShows(showId: Int, page: Int): Observable<ShowsListResponseEntity>
    fun searchTvShows(query: String, page: Int): Observable<ShowsListResponseEntity>
}