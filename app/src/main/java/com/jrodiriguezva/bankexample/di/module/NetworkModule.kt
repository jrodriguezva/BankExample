package com.jrodiriguezva.bankexample.di.module

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jrodiriguezva.bankexample.BuildConfig
import com.jrodiriguezva.bankexample.data.remote.BankTransactionService
import com.jrodiriguezva.bankexample.data.remote.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverService(@NonNull retrofit: Retrofit): BankTransactionService {
        return retrofit.create(BankTransactionService::class.java)
    }

}