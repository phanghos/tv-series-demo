package com.taitascioredev.android.tvseriesdemo.data.repository.impl

import com.taitascioredev.android.tvseriesdemo.data.entity.mapper.ShowsListMapper
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/01/18.
 */
class MovieRepositoryImpl @Inject constructor(
        private val service: MovieDbService,
        private val mapper: ShowsListMapper
) : MovieRepository {

    override fun getPopularTvShows(page: Int): Observable<List<MovieDbTvShow>> {
        return service.getPopularTvShows(page)
                .map { mapper.map(it) }
    }

    override fun getSimilarTvShows(showId: Int, page: Int): Observable<List<MovieDbTvShow>> {
        return service.getSimilarTvShows(showId, page).map { mapper.map(it) }
    }

    override fun searchTvShows(query: String, page: Int): Observable<List<MovieDbTvShow>> {
        return service.searchTvShows(query, page).map { mapper.map(it) }
    }
}