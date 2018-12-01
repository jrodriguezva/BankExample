package com.jrodiriguezva.bankexample.data.model.network

data class BankTransactionListResponse(
    var id: Int,
    var date: String?,
    var amount: Float?,
    var fee: Float?,
    var description: String?
)