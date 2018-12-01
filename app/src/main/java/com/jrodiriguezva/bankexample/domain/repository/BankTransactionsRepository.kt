package com.jrodiriguezva.bankexample.domain.repository

import androidx.lifecycle.LiveData
import com.google.gson.internal.bind.util.ISO8601Utils
import com.jrodiriguezva.bankexample.data.local.BankTransactionDao
import com.jrodiriguezva.bankexample.data.model.entity.BankTransaction
import com.jrodiriguezva.bankexample.data.model.network.BankTransactionListResponse
import com.jrodiriguezva.bankexample.data.remote.ApiResponse
import com.jrodiriguezva.bankexample.data.remote.BankTransactionService
import com.jrodiriguezva.bankexample.domain.repository.model.Resource
import timber.log.Timber
import java.text.ParseException
import java.text.ParsePosition
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankTransactionsRepository @Inject
constructor(val service: BankTransactionService, val bankTransactionDao: BankTransactionDao) : Repository {

    init {
        Timber.d("Injection TransactionsRepository")
    }

    fun loadBankTransactionList(): LiveData<Resource<List<BankTransaction>>> {
        return object : NetworkBoundRepository<List<BankTransaction>, List<BankTransactionListResponse>>() {
            override fun saveFetchData(items: List<BankTransactionListResponse>) {
                items.forEach { bankTransaction ->
                    bankTransactionDao.getBankTransaction(bankTransaction.id)?.let { trans ->
                        //Check date is valid and is before to update it
                        try {
                            val dateNetwork = ISO8601Utils.parse(bankTransaction.date, ParsePosition(0))
                            if (dateNetwork?.before(trans.date) == true) {
                                with(trans) {
                                    date = dateNetwork
                                    amount = bankTransaction.amount
                                    description = bankTransaction.description
                                    fee = bankTransaction.fee
                                }
                                bankTransactionDao.updateTransaction(trans)
                            }
                        } catch (e: ParseException) {

                        }


                    } ?: kotlin.run {
                        try {
                            val dateNetwork = ISO8601Utils.parse(bankTransaction.date, ParsePosition(0))
                            bankTransactionDao.insertTransaction(
                                BankTransaction(
                                    id = bankTransaction.id,
                                    date = dateNetwork,
                                    amount = bankTransaction.amount,
                                    description = bankTransaction.description,
                                    fee = bankTransaction.fee
                                )
                            )
                        } catch (e: ParseException) {

                        }
                    }
                }

            }

            override fun shouldFetch(data: List<BankTransaction>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<BankTransaction>> {
                return bankTransactionDao.getBankTransactionList()
            }

            override fun fetchService(): LiveData<ApiResponse<List<BankTransactionListResponse>>> {
                return service.getAllTransaction()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
}