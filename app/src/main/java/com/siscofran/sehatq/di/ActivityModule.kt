package com.siscofran.sehatq.di

import com.siscofran.sehatq.ui.detail.DetailActivity
import com.siscofran.sehatq.ui.history.HistoryActivity
import com.siscofran.sehatq.ui.main.MainActivity
import com.siscofran.sehatq.ui.main.MainFragmentModule
import com.siscofran.sehatq.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun bindHistoryActivity(): HistoryActivity

}