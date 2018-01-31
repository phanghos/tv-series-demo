package com.taitascioredev.android.tvseriesdemo.util

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by rrtatasciore on 27/01/18.
 */
val Fragment.baseActivity: AppCompatActivity
        get() = activity as AppCompatActivity

fun AppCompatActivity.enableUpNavigation() {
    val actionBar = supportActionBar
    actionBar?.let {
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}

fun AppCompatActivity.disableUpNavigation() {
    val actionBar = supportActionBar
    actionBar?.let {
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayHomeAsUpEnabled(false)
    }
}

fun AppCompatActivity.snackbar(v: View, msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(v, msg, duration).show()
}

fun Fragment.snackbar(v: View, msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(v, msg, duration).show()
}