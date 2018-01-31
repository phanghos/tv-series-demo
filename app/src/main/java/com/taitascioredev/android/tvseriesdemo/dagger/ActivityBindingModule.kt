package com.taitascioredev.android.tvseriesdemo.dagger

import com.taitascioredev.android.tvseriesdemo.MainActivity
import com.taitascioredev.android.tvseriesdemo.dagger.searchtvshows.SearchTvShowsModule
import com.taitascioredev.android.tvseriesdemo.dagger.tvshowdetails.ShowDetailsModule
import com.taitascioredev.android.tvseriesdemo.dagger.tvshowslist.ShowsListModule
import com.taitascioredev.android.tvseriesdemo.feature.searchtvshows.view.SearchResultsActivity
import com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view.ShowDetailsFragment
import com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view.ShowsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrtatasciore on 25/01/18.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [ShowsListModule::class, ShowDetailsModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ShowsListModule::class])
    abstract fun provideShowsListFragment(): ShowsListFragment

    @ContributesAndroidInjector(modules = [ShowDetailsModule::class])
    abstract fun provideShowDetailsFragment(): ShowDetailsFragment

    @ContributesAndroidInjector(modules = [SearchTvShowsModule::class])
    abstract fun provideSearchResultsActivity(): SearchResultsActivity
}