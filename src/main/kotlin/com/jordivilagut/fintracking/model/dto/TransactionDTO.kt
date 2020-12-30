package com.jordivilagut.fintracking.model.dto

class TransactionDTO(
        val id: String,
        val date: Long,
        val amount: Number,
        val description: String,
        val expenseType: ExpenseType)