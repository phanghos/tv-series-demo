package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taitascioredev.android.tvseriesdemo.data.repository.MovieRepository
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
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
class GetPopularTvShowsUseCaseImplTest {

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

    @Mock lateinit var repository: MovieRepository

    lateinit var useCase: GetPopularTvShowsUseCase

    @Before
    fun setUp() {
        initMocks(this)
        useCase = GetPopularTvShowsUseCaseImpl(repository)
    }

    @Test
    fun getPopularTvShows_should_return_expected_value() {
        // Given
        val typeToken = object : TypeToken<List<MovieDbTvShow>>() {}.type
        val expectedValue = Gson().fromJson<List<MovieDbTvShow>>(JSON, typeToken)
        val expectedObservable = Observable.just(expectedValue)
        `when`(repository.getPopularTvShows(1)).thenReturn(expectedObservable)

        // When
        val resultObservable = repository.getPopularTvShows(1)

        // Then
        verify(repository).getPopularTvShows(1)
        resultObservable.test().assertValueCount(1)
        resultObservable.test().assertValue(expectedValue)
        resultObservable.test().assertComplete()
        resultObservable.test().assertNoErrors()
    }
}