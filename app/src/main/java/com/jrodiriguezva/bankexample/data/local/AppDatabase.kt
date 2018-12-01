package com.jrodiriguezva.bankexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jrodiriguezva.bankexample.data.model.entity.BankTransaction

@Database(entities = [BankTransaction::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun bankTransactionDao(): BankTransactionDao
}