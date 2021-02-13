package com.jordivilagut.fintracking.model.dto

import com.jordivilagut.fintracking.model.dto.OperationType.EXPENSE

class CreateBudgetItemDTO(
    val amount: Double,
    val description: String,
    val start: Long,
    val end: Long?,
    val expenseType: ExpenseType,
    val operationType: OperationType,
    val paymentType: PaymentType,
    val paymentRecurrence: PaymentRecurrence?

) {

    fun isExpense() = operationType == EXPENSE
}