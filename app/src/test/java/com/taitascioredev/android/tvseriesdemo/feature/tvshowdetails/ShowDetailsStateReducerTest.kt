package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails

import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate.ShowDetailsViewState
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created by rrtatasciore on 28/01/18.
 */
@RunWith(Parameterized::class)
class ShowDetailsStateReducerTest(val result: ShowDetailsResult, val state: ShowDetailsViewState) {

    lateinit var stateReducer: ShowDetailsStateReducer

    @Before
    fun setUp() {
        stateReducer = ShowDetailsStateReducer()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun values(): Collection<Array<Any>> {
            val value: List<MovieDbTvShow> = ArrayList()
            val t = Exception()
            return listOf(
                    arrayOf(ShowDetailsResult.success(value), ShowDetailsViewState.success(value)),
                    arrayOf(ShowDetailsResult.error(t), ShowDetailsViewState.error(t)),
                    arrayOf(ShowDetailsResult.inFlight(), ShowDetailsViewState.inFlight())
            )
        }
    }

    @Test
    fun apply_should_return_expected_view_state() {
        // When
        val resultViewState = stateReducer.apply(ShowDetailsViewState.idle(), result)

        // Then
        assertEquals(state, resultViewState)
    }
}