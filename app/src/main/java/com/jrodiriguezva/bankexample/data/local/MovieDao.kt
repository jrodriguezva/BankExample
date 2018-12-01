package com.jrodiriguezva.bankexample.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jrodiriguezva.bankexample.data.model.entity.BankTransaction


@Dao
interface BankTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactionList(transactionList: List<BankTransaction>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: BankTransaction)

    @Update
    fun updateTransaction(bankTransaction: BankTransaction)

    @Query("SELECT * FROM BANK_TRANSACTIONS WHERE id = :id")
    fun getBankTransaction(id: Int): BankTransaction?

    @Query("SELECT * FROM BANK_TRANSACTIONS")
    fun getBankTransactionList(): LiveData<List<BankTransaction>>

}