package com.jrodiriguezva.bankexample.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrodiriguezva.bankexample.presentation.ui.main.BankTransactionAdapter
import com.jrodiriguezva.bankexample.presentation.ui.main.BankTransactionView

@BindingAdapter("data")
fun RecyclerView.setRecyclerViewProperties(data: List<BankTransactionView>) {
    if (this.adapter is BankTransactionAdapter) {
        (this.adapter as BankTransactionAdapter).collection = data
    }
}