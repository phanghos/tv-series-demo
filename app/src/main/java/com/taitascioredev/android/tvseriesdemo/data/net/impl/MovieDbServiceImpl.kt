package com.taitascioredev.android.tvseriesdemo.data.net.impl

import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbApi
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/01/18.
 */
class MovieDbServiceImpl @Inject constructor(private val api: MovieDbApi) : MovieDbService {
    override fun getPopularTvShows(page: Int) = api.getPopularTvShows(page)
    override fun getSimilarTvShows(showId: Int, page: Int) = api.getSimilarTvShows(showId, page)
    override fun searchTvShows(query: String, page: Int) = api.searchTvShows(query, page)
}