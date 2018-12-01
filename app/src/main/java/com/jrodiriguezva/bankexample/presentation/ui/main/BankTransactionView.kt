package com.jrodiriguezva.bankexample.presentation.ui.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.jrodiriguezva.bankexample.BR
import java.util.*

class BankTransactionView(date: Date, total: Float, description: String) : BaseObservable() {


    @get:Bindable
    var date: Date = date
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }

    @get:Bindable
    var total: Float = total
        set(value) {
            field = value
            notifyPropertyChanged(BR.total)
        }

    @get:Bindable
    var description: String = description
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }
}