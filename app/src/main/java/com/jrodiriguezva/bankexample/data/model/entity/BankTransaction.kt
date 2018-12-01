package com.jrodiriguezva.bankexample.data.model.entity

import androidx.room.Entity
import com.jrodiriguezva.bankexample.domain.repository.model.NetworkResponseModel
import java.util.*

@Entity(primaryKeys = ["id"], tableName = "BANK_TRANSACTIONS")
data class BankTransaction(
    var id: Int,
    var date: Date?,
    var amount: Float?,
    var fee: Float?,
    var description: String?
)