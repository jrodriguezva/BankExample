package com.jrodiriguezva.bankexample.data.remote

import androidx.lifecycle.LiveData
import com.jrodiriguezva.bankexample.data.model.network.BankTransactionListResponse
import retrofit2.http.GET

interface BankTransactionService {
    /**
     * Get all transactions
     */
    @GET("bins/1a30k8")
    fun getAllTransaction(): LiveData<ApiResponse<List<BankTransactionListResponse>>>

}