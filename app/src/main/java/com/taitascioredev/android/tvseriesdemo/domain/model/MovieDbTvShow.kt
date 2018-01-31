package com.taitascioredev.android.tvseriesdemo.domain.model

import java.io.Serializable

/**
 * Created by rrtatasciore on 24/01/18.
 */
data class MovieDbTvShow(
        val id: Int? = null,
        val name: String? = null,
        val overview: String? = null,
        val posterPath: String? = null,
        val voteAverage: Double? = null,
        val voteCount: Int? = null
) : Serializable