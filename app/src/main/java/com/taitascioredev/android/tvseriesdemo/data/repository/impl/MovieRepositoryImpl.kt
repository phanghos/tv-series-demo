package com.taitascioredev.android.tvseriesdemo.data.repository.impl

import com.taitascioredev.android.tvseriesdemo.data.entity.mapper.ShowsListMapper
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/01/18.
 */
class MovieRepositoryImpl @Inject constructor(
        private val service: MovieDbService,
        private val mapper: ShowsListMapper
) : MovieRepository {

    private val list: List<MovieDbTvShow> = ArrayList()

    override fun getPopularTvShows(page: Int): Observable<List<MovieDbTvShow>> {
        return service.getPopularTvShows(page)
                .map {
                    val newList = mapper.map(it)
                    mergeResults(newList)
                    list
                }
    }

    override fun getSimilarTvShows(showId: Int, page: Int): Observable<List<MovieDbTvShow>> {
        return service.getSimilarTvShows(showId, page).map { mapper.map(it) }
    }

    override fun searchTvShows(query: String, page: Int): Observable<List<MovieDbTvShow>> {
        return service.searchTvShows(query, page).map { mapper.map(it) }
    }

    private fun mergeResults(newList: List<MovieDbTvShow>) {
        newList.forEach { (list as ArrayList).add(it) }
    }
}