package com.jordivilagut.fintracking.model.dto

import java.util.*

class TransactionDTO(
        val id: String,
        val date: Date,
        val amount: Number,
        val description: String,
        val operationType: OperationType,
        val expenseType: ExpenseType)