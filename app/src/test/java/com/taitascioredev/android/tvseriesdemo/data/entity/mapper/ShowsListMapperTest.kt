package com.taitascioredev.android.tvseriesdemo.data.entity.mapper

import com.taitascioredev.android.tvseriesdemo.data.entity.ResultsItem
import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by rrtatasciore on 28/01/18.
 */
class ShowsListMapperTest {

    lateinit var mapper: ShowsListMapper

    @Before
    fun setUp() {
        mapper = ShowsListMapper()
    }

    @Test
    fun map_should_return_expected_value() {
        // Given
        val resultsItem = ResultsItem(
                id = 1,
                name = "test",
                overview = "test",
                posterPath = "test")
        val expectedValue = MovieDbTvShow(
                id = 1,
                name = "test",
                overview = "test",
                posterPath = "test")
        val expectedList: List<MovieDbTvShow> = ArrayList()
        (expectedList as ArrayList).add(expectedValue)

        // When
        val mappedValue = mapper.map(resultsItem)

        // Then
        assertEquals(expectedValue, mappedValue)
    }

    @Test
    fun map_should_return_expected_list() {
        // Given
        val resultsItem = ResultsItem(
                id = 1,
                name = "test",
                overview = "test",
                posterPath = "test")
        val list: List<ResultsItem> = ArrayList()
        (list as ArrayList).add(resultsItem)
        val entity = ShowsListResponseEntity(results = list)
        val expectedValue = MovieDbTvShow(
                id = 1,
                name = "test",
                overview = "test",
                posterPath = "test")
        val expectedList: List<MovieDbTvShow> = ArrayList()
        (expectedList as ArrayList).add(expectedValue)

        // When
        val mappedValue = mapper.map(resultsItem)
        val mappedList = mapper.map(entity)

        // Then
        assertEquals(expectedValue, mappedValue)
        assertEquals(expectedList, mappedList)
    }
}