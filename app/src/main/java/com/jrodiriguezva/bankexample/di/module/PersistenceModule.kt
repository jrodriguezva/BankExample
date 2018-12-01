package com.jrodiriguezva.bankexample.di.module

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.jrodiriguezva.bankexample.data.local.AppDatabase
import com.jrodiriguezva.bankexample.data.local.BankTransactionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "Transaction.db").allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AppDatabase): BankTransactionDao {
        return database.bankTransactionDao()
    }
}