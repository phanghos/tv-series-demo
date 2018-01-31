package com.taitascioredev.android.tvseriesdemo.data.net

import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rrtatasciore on 24/01/18.
 */
interface MovieDbApi {

    @GET("tv/popular?api_key=49448719806c54a042b9fd76533eaea0")
    fun getPopularTvShows(@Query("page") page: Int): Observable<ShowsListResponseEntity>

    @GET("tv/{show_id}/similar?api_key=49448719806c54a042b9fd76533eaea0")
    fun getSimilarTvShows(@Path("show_id") showId: Int, @Query("page") page: Int): Observable<ShowsListResponseEntity>

    @GET("search/tv?api_key=49448719806c54a042b9fd76533eaea0")
    fun searchTvShows(@Query("query") query: String, @Query("page") page: Int): Observable<ShowsListResponseEntity>
}