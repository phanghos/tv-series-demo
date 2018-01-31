package com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.impl

import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.usecase.SearchTvShowsUseCase
import javax.inject.Inject

/**
 * Created by rrtatasciore on 30/01/18.
 */
class SearchTvShowsUseCaseImpl @Inject constructor(private val repository: MovieRepository) : SearchTvShowsUseCase {
    override fun searchTvShows(query: String, page: Int) = repository.searchTvShows(query, page)
}