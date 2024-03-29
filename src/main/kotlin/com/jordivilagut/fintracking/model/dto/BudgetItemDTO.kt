package com.jordivilagut.fintracking.model.dto

class BudgetItemDTO(
    val id: String,
    val start: Long,
    val end: Long?,
    val amount: Number,
    val description: String,
    val operationType: OperationType,
    val expenseType: ExpenseType,
    val paymentType: PaymentType,
    val recurrence: PaymentRecurrence?)