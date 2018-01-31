package com.taitascioredev.android.tvseriesdemo.presentation.view

import android.support.v4.app.Fragment
import com.taitascioredev.android.tvseriesdemo.presentation.base.BaseView
import com.taitascioredev.android.tvseriesdemo.presentation.base.Intent
import com.taitascioredev.android.tvseriesdemo.presentation.base.ViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rrtatasciore on 31/01/18.
 */
abstract class BaseFragment<I: Intent, VS : ViewState> : Fragment(), BaseView<I, VS> {

    private val disposables = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}