package com.taitascioredev.android.tvseriesdemo.data.repository.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taitascioredev.android.tvseriesdemo.data.entity.ResultsItem
import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import com.taitascioredev.android.tvseriesdemo.data.entity.mapper.ShowsListMapper
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

/**
 * Created by rrtatasciore on 28/01/18.
 */
class MovieRepositoryImplTest {

    private val JSON = "[{\n" +
            "\t\t\"original_name\": \"The Big Bang Theory\",\n" +
            "\t\t\"genre_ids\": [35],\n" +
            "\t\t\"name\": \"The Big Bang Theory\",\n" +
            "\t\t\"popularity\": 187.494438,\n" +
            "\t\t\"origin_country\": [\"US\"],\n" +
            "\t\t\"vote_count\": 2494,\n" +
            "\t\t\"first_air_date\": \"2007-09-24\",\n" +
            "\t\t\"backdrop_path\": \"\\/nGsNruW3W27V6r4gkyc3iiEGsKR.jpg\",\n" +
            "\t\t\"original_language\": \"en\",\n" +
            "\t\t\"id\": 1418,\n" +
            "\t\t\"vote_average\": 6.9,\n" +
            "\t\t\"overview\": \"The Big Bang Theory is centered on five characters living in " +
            "Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a " +
            "waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's " +
            "equally geeky and socially awkward friends and co-workers, mechanical engineer " +
            "Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of " +
            "the four guys is contrasted for comic effect with Penny's social skills and common " +
            "sense.\",\n" +
            "\t\t\"poster_path\": \"\\/ooBGRQBdbGzBxAVfExiO8r7kloA.jpg\"\n" +
            "\t}]"

    @Mock lateinit var service: MovieDbService

    lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun getPopularTvShows_with_mock_mapper_should_return_expected_value() {
        // Given
        val mapper = mock<ShowsListMapper>(ShowsListMapper::class.java)
        repository = MovieRepositoryImpl(service, mapper)
        val showsListResponseEntity = ShowsListResponseEntity()
        val expectedValue = ArrayList<MovieDbTvShow>()
        val expectedObservable = Observable.just(showsListResponseEntity)
        `when`(service.getPopularTvShows(1)).thenReturn(expectedObservable)
        `when`(mapper.map(showsListResponseEntity)).thenReturn(expectedValue)

        // When
        val resultObservable = repository.getPopularTvShows(1)

        // Then
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }

    @Test
    fun getPopularTvShows_with_real_mapper_should_return_expected_value() {
        // Given
        val mapper = ShowsListMapper()
        repository = MovieRepositoryImpl(service, mapper)
        val showsListResponseEntity = ShowsListResponseEntity()
        val typeToken = object : TypeToken<List<ResultsItem>>() {}.type
        val list = Gson().fromJson<List<ResultsItem>>(JSON, typeToken)
        showsListResponseEntity.results = list
        val expectedValue = mapper.map(showsListResponseEntity)
        val expectedObservable = Observable.just(showsListResponseEntity)
        `when`(service.getPopularTvShows(1)).thenReturn(expectedObservable)

        // When
        val resultObservable = repository.getPopularTvShows(1)

        // Then
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }

    @Test
    fun getSimilarTvShows_with_mock_mapper_should_return_expected_value() {
        // Given
        val mapper = mock<ShowsListMapper>(ShowsListMapper::class.java)
        repository = MovieRepositoryImpl(service, mapper)
        val showsListResponseEntity = ShowsListResponseEntity()
        val expectedValue = ArrayList<MovieDbTvShow>()
        val expectedObservable = Observable.just(showsListResponseEntity)
        `when`(service.getSimilarTvShows(0, 1)).thenReturn(expectedObservable)
        `when`(mapper.map(showsListResponseEntity)).thenReturn(expectedValue)

        // When
        val resultObservable = repository.getSimilarTvShows(0, 1)

        // Then
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }

    @Test
    fun getSimilarTvShows_with_real_mapper_should_return_expected_value() {
        // Given
        val mapper = ShowsListMapper()
        repository = MovieRepositoryImpl(service, mapper)
        val showsListResponseEntity = ShowsListResponseEntity()
        val typeToken = object : TypeToken<List<ResultsItem>>() {

        }.type
        val list = Gson().fromJson<List<ResultsItem>>(JSON, typeToken)
        showsListResponseEntity.results = list
        val expectedValue = mapper.map(showsListResponseEntity)
        val expectedObservable = Observable.just(showsListResponseEntity)
        `when`(service.getSimilarTvShows(0, 1)).thenReturn(expectedObservable)

        // When
        val resultObservable = repository.getSimilarTvShows(0, 1)

        // Then
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}