package com.jrodiriguezva.bankexample.di.component

import android.app.Application
import com.jrodiriguezva.bankexample.di.module.ActivityModule
import com.jrodiriguezva.bankexample.di.module.NetworkModule
import com.jrodiriguezva.bankexample.di.module.PersistenceModule
import com.jrodiriguezva.bankexample.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        PersistenceModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}