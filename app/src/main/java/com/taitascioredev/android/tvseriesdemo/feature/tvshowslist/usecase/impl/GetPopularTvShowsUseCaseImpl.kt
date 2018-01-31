package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.impl

import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/01/18.
 */
class GetPopularTvShowsUseCaseImpl @Inject constructor(private val repository: MovieRepository) : GetPopularTvShowsUseCase {
    override fun getPopularTvShows(page: Int) = repository.getPopularTvShows(page)
}