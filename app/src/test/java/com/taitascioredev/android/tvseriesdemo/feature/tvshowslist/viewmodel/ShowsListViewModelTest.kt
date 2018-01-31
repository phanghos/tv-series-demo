package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewmodel

import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListActionProcessor
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListIntent
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.ShowsListStateReducer
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.usecase.GetPopularTvShowsUseCase
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.viewstate.ShowsListViewState
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

/**
 * Created by rrtatasciore on 28/01/18.
 */
class ShowsListViewModelTest {

    @Mock lateinit var getPopularTvShowsUseCase: GetPopularTvShowsUseCase

    lateinit var viewModel: ShowsListViewModel

    @Before
    fun setUp() {
        initMocks(this)
        val actionProcessor = ShowsListActionProcessor(getPopularTvShowsUseCase)
        val stateReducer = ShowsListStateReducer()
        viewModel = ShowsListViewModel(actionProcessor, stateReducer)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun processIntents_InitialIntent_Success() {
        // Given
        val expectedValue = ArrayList<MovieDbTvShow>()
        val expectedObservable = Observable.just<List<MovieDbTvShow>>(expectedValue)
        `when`(getPopularTvShowsUseCase.getPopularTvShows(1)).thenReturn(expectedObservable)
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowsListIntent.InitialIntent()))

        // Then
        verify(getPopularTvShowsUseCase).getPopularTvShows(1)
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowsListViewState.idle())
        testObserver.assertValueAt(1, ShowsListViewState.inFlight())
        testObserver.assertValueAt(2, ShowsListViewState.success(expectedValue))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_InitialIntent_Error() {
        // Given
        val expectedException = Exception()
        `when`(getPopularTvShowsUseCase.getPopularTvShows(1)).thenReturn(
                Observable.error<List<MovieDbTvShow>>(expectedException))
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowsListIntent.InitialIntent()))

        // Then
        verify(getPopularTvShowsUseCase).getPopularTvShows(1)
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowsListViewState.idle())
        testObserver.assertValueAt(1, ShowsListViewState.inFlight())
        testObserver.assertValueAt(2, ShowsListViewState.error(expectedException))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_LoadIntent_Success() {
        // Given
        val expectedValue = ArrayList<MovieDbTvShow>()
        val expectedObservable = Observable.just<List<MovieDbTvShow>>(expectedValue)
        `when`(getPopularTvShowsUseCase.getPopularTvShows(1)).thenReturn(expectedObservable)
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowsListIntent.LoadIntent()))

        // Then
        verify(getPopularTvShowsUseCase).getPopularTvShows(1)
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowsListViewState.idle())
        testObserver.assertValueAt(1, ShowsListViewState.inFlight())
        testObserver.assertValueAt(2, ShowsListViewState.success(expectedValue))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun processIntents_LoadIntent_Error() {
        // Given
        val expectedException = Exception()
        `when`(getPopularTvShowsUseCase.getPopularTvShows(1)).thenReturn(
                Observable.error<List<MovieDbTvShow>>(expectedException))
        val testObserver = viewModel.states().test()

        // When
        viewModel.processIntents(Observable.just(ShowsListIntent.LoadIntent()))

        // Then
        verify(getPopularTvShowsUseCase).getPopularTvShows(1)
        testObserver.assertValueCount(3)
        testObserver.assertValueAt(0, ShowsListViewState.idle())
        testObserver.assertValueAt(1, ShowsListViewState.inFlight())
        testObserver.assertValueAt(2, ShowsListViewState.error(expectedException))
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}