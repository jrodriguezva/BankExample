package com.jrodiriguezva.bankexample.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrodiriguezva.bankexample.databinding.ItemBankTransactionBinding
import kotlin.properties.Delegates

class BankTransactionAdapter : RecyclerView.Adapter<BankTransactionAdapter.ViewHolder>() {

    internal var collection: List<BankTransactionView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemBankTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(viewHolder: BankTransactionAdapter.ViewHolder, position: Int) =
        viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(private val itemBankTransactionBinding: ItemBankTransactionBinding) :
        RecyclerView.ViewHolder(itemBankTransactionBinding.root) {
        fun bind(bankTransactionView: BankTransactionView) {
            itemBankTransactionBinding.bankTransaction = bankTransactionView
        }
    }
}