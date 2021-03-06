package com.jordivilagut.fintracking.model.dto

import com.jordivilagut.fintracking.model.dto.OperationType.EXPENSE

class CreateTransactionDTO(
    val amount: Double,
    val date: Long,
    val description: String,
    val expenseType: ExpenseType,
    val operationType: OperationType) {

    fun isExpense() = operationType == EXPENSE
}