package com.taitascioredev.android.tvseriesdemo.data.entity.mapper

import com.taitascioredev.android.tvseriesdemo.data.entity.ResultsItem
import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow

/**
 * Created by rrtatasciore on 24/01/18.
 */
open class ShowsListMapper {

    open fun map(showsListResponseEntity: ShowsListResponseEntity): List<MovieDbTvShow> {
        val list = ArrayList<MovieDbTvShow>()
        showsListResponseEntity.results?.forEach { list.add(map(it!!)) }
        return list
    }

    open fun map(resultsItem: ResultsItem): MovieDbTvShow {
        val tvShow = MovieDbTvShow(
                id = resultsItem.id,
                name = resultsItem.name,
                overview = resultsItem.overview,
                posterPath = resultsItem.posterPath,
                voteAverage = resultsItem.voteAverage,
                voteCount = resultsItem.voteCount)
        return tvShow
    }
}