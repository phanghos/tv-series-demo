package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewmodel

import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.ShowDetailsStateReducer
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.usecase.GetSimilarTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.viewstate.ShowDetailsViewState
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.lang.Exception

/**
 * Created by rrtatasciore on 28/01/18.
 */
class ShowDetailsViewModelTest {

    @Mock lateinit var useCase: GetSimilarTvShowsUseCase

    lateinit var viewModel: ShowDetailsViewModel

    @Before
    fun setUp() {
        initMocks(this)
        val actionProcessor = ShowDetailsActionProcessor(useCase)
        val stateReducer = ShowDetailsStateReducer()
        viewModel = ShowDetailsViewModel(actionProcessor, stateReducer)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun processIntents_InitialIntent_Success() {
        // Given
        val expectedValue: List<MovieDbTvShow> = ArrayList()
        val expectedObservable = Observable.just(expectedValue)
        `when`(useCase.getSimilarTvShows(0, 1)).thenReturn(expectedObservable)
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowDetailsIntent.InitialIntent.create(0)))

        // Then
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowDetailsViewState.idle())
        testObserver.assertValueAt(1, ShowDetailsViewState.inFlight())
        testObserver.assertValueAt(2, ShowDetailsViewState.success(expectedValue))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_InitialIntent_Error() {
        // Given
        val expectedException = Exception()
        `when`(useCase.getSimilarTvShows(0, 1)).thenReturn(Observable.error(expectedException))
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowDetailsIntent.InitialIntent.create(0)))

        // Then
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowDetailsViewState.idle())
        testObserver.assertValueAt(1, ShowDetailsViewState.inFlight())
        testObserver.assertValueAt(2, ShowDetailsViewState.error(expectedException))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_LoadIntent_Success() {
        // Given
        val expectedValue: List<MovieDbTvShow> = ArrayList()
        val expectedObservable = Observable.just(expectedValue)
        `when`(useCase.getSimilarTvShows(0, 1)).thenReturn(expectedObservable)
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowDetailsIntent.LoadIntent.create(0)))

        // Then
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowDetailsViewState.idle())
        testObserver.assertValueAt(1, ShowDetailsViewState.inFlight())
        testObserver.assertValueAt(2, ShowDetailsViewState.success(expectedValue))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_LoadIntent_Error() {
        // Given
        val expectedException = Exception()
        `when`(useCase.getSimilarTvShows(0, 1)).thenReturn(Observable.error(expectedException))
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowDetailsIntent.LoadIntent.create(0)))

        // Then
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowDetailsViewState.idle())
        testObserver.assertValueAt(1, ShowDetailsViewState.inFlight())
        testObserver.assertValueAt(2, ShowDetailsViewState.error(expectedException))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun trimContent_should_return_original_string() {
        // Given
        val text = "this is a test"

        // When
        val resultText = viewModel.trimContent(text, 10)

        // Then
        Assert.assertEquals(text, resultText)
    }

    @Test
    fun trimContent_should_return_shorter_string() {
        // Given
        val text = "this is a test"
        val expectedValue = "this is a (...)"

        // When
        val resultText = viewModel.trimContent(text, 3)

        // Then
        Assert.assertEquals(expectedValue, resultText)
    }

    @Test
    fun trimContent_should_return_empty_string() {
        // Given
        var text: String? = null

        // When
        val resultText = viewModel.trimContent(text, 3)

        // Then
        Assert.assertEquals("", resultText)
    }
}