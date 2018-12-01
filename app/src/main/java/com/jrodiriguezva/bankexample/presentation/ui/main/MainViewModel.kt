package com.jrodiriguezva.bankexample.presentation.ui.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jrodiriguezva.bankexample.data.model.entity.BankTransaction
import com.jrodiriguezva.bankexample.domain.repository.BankTransactionsRepository
import com.jrodiriguezva.bankexample.domain.repository.model.Resource
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

class MainViewModel @Inject
constructor(bankTransactionsRepository: BankTransactionsRepository) : ViewModel() {
    val bankTransactionView: ObservableArrayList<BankTransactionView> = ObservableArrayList()
    var loadBankTransactionList: LiveData<Resource<List<BankTransaction>>> =
        bankTransactionsRepository.loadBankTransactionList()
    val bankTransactionViewFirst: BankTransactionView = BankTransactionView(Date(), 0f, "")

    fun addData(it: Resource<List<BankTransaction>>) {
        it.data?.sortedByDescending { it.date }?.let { bank ->
            with(bank.first()) {
                bankTransactionViewFirst.date = date ?: Date()
                bankTransactionViewFirst.total = ((amount ?: 0f) - abs(fee ?: 0f))
                bankTransactionViewFirst.description = description ?: ""

            }
            bank.drop(1).forEach {
                with(it) {
                    bankTransactionView.add(
                        BankTransactionView(
                            date = date ?: Date(),
                            total = ((amount ?: 0f) - abs(fee ?: 0f)),
                            description = description ?: ""
                        )
                    )
                }
            }
        }
    }


}