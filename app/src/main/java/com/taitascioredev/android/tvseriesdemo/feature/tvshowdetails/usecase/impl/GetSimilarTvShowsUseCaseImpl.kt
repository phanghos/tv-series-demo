package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.impl

import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.GetSimilarTvShowsUseCase
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class GetSimilarTvShowsUseCaseImpl @Inject constructor(private val repository: MovieRepository) : GetSimilarTvShowsUseCase {
    override fun getSimilarTvShows(showId: Int, page: Int) = repository.getSimilarTvShows(showId, page)
}