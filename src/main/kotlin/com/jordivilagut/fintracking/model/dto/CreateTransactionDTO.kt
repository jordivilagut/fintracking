package com.jordivilagut.fintracking.model.dto

class CreateTransactionDTO(
    val amount: Number,
    val description: String,
    val expenseType: ExpenseType)