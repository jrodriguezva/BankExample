package com.jrodiriguezva.bankexample.di.module

import com.jrodiriguezva.bankexample.presentation.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

}