package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist

import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by rrtatasciore on 28/01/18.
 */
@RunWith(Parameterized::class)
class ShowsListStateReducerTest(val result: ShowsListResult, val state: ShowsListViewState) {

    lateinit var stateReducer: ShowsListStateReducer

    @Before
    fun setUp() {
        stateReducer = ShowsListStateReducer()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): Collection<Array<Any>> {
            val value: List<MovieDbTvShow> = ArrayList()
            val t = Exception()
            return listOf(
                    arrayOf(ShowsListResult.success(value), ShowsListViewState.success(value)),
                    arrayOf(ShowsListResult.error(t), ShowsListViewState.error(t)),
                    arrayOf(ShowsListResult.inFlight(), ShowsListViewState.inFlight())
            )
        }
    }

    @Test
    fun apply_should_return_expected_view_state() {
        // When
        val resultViewState = stateReducer.apply(ShowsListViewState.idle(), result)

        // Then
        assertEquals(state, resultViewState)
    }
}