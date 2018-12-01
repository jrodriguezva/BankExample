package com.jrodiriguezva.bankexample.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(pattern: String): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(this)
}

fun Date.toSimpleString(): String {
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return format.format(this)
}

