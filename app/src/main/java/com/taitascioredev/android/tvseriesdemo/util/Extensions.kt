package com.taitascioredev.android.tvseriesdemo.util

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

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