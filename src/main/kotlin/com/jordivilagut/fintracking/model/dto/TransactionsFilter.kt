package com.jordivilagut.fintracking.model.dto

import java.util.*

class TransactionsFilter(
    val from: Date,
    val to: Date,
    val skip: Int,
    val limit: Int,
    val expenseType: ExpenseType?,
    val minAmount: Number?,
    val maxAmount: Number?)