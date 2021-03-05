package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.OperationType.EXPENSE
import com.jordivilagut.fintracking.model.dto.OperationType.INCOME

open class BaseTransaction(
    val amount: Double,
    val description: String,
    val operationType: OperationType) {

    fun isExpense() = operationType == EXPENSE

    fun isIncome() = operationType == INCOME
}