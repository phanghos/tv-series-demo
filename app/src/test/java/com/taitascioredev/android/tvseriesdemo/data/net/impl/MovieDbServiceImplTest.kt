package com.taitascioredev.android.tvseriesdemo.data.net.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taitascioredev.android.tvseriesdemo.data.entity.ShowsListResponseEntity
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbApi
import com.taitascioredev.android.tvseriesdemo.data.net.MovieDbService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by rrtatasciore on 28/01/18.
 */
class MovieDbServiceImplTest {

    private val JSON = "{\n" +
            "\t\"page\": 1,\n" +
            "\t\"total_results\": 20028,\n" +
            "\t\"total_pages\": 1002,\n" +
            "\t\"results\": [{\n" +
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
            "\t}]\n" +
            "}"

    @Mock lateinit var api: MovieDbApi

    lateinit var service: MovieDbService

    @Before
    fun setUp() {
        initMocks(this)
        service = MovieDbServiceImpl(api)
    }

    @Test
    fun getPopularTvShows_should_return_expected_value() {
        // Given
        val typeToken = object : TypeToken<ShowsListResponseEntity>(){}.type
        val showsListResponseEntity = Gson().fromJson<ShowsListResponseEntity>(JSON, typeToken)
        val expectedValue = Observable.just(showsListResponseEntity)
        `when`(api.getPopularTvShows(1)).thenReturn(expectedValue)

        // When
        val resultObservable = api.getPopularTvShows(1)

        // Then
        verify(api).getPopularTvShows(1)
        resultObservable.test().assertValueCount(1)
        resultObservable.test().assertValue(showsListResponseEntity)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }

    @Test
    fun getSimilarTvShows_should_return_expected_value() {
        // Given
        val typeToken = object : TypeToken<ShowsListResponseEntity>() {

        }.type
        val showsListResponseEntity = Gson().fromJson<ShowsListResponseEntity>(JSON, typeToken)
        val expectedValue = Observable.just(showsListResponseEntity)
        `when`(api.getSimilarTvShows(0, 1)).thenReturn(expectedValue)

        // When
        val resultObservable = api.getSimilarTvShows(0, 1)

        // Then
        verify(api).getSimilarTvShows(0, 1)
        resultObservable.test().assertValueCount(1)
        resultObservable.test().assertValue(showsListResponseEntity)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}