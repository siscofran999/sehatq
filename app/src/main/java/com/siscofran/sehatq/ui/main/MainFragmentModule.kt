package com.siscofran.sehatq.ui.main

import com.siscofran.sehatq.di.FragmentScope
import com.siscofran.sehatq.ui.main.home.HomeFragment
import com.siscofran.sehatq.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideProfileFragment(): ProfileFragment
}