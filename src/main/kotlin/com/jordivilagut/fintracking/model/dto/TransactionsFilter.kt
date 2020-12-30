package com.jordivilagut.fintracking.model.dto

class TransactionsFilter(
    val from: Long,
    val to: Long,
    val skip: Int?,
    val limit: Int?,
    val expenseType: ExpenseType?,
    val minAmount: Number?,
    val maxAmount: Number?)