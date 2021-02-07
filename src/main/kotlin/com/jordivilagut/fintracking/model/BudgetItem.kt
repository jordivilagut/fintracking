package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence
import com.jordivilagut.fintracking.model.dto.PaymentType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("budgetItems")
class BudgetItem(
    @Id var id: ObjectId?,
    val userId: ObjectId,
    val start: Date,
    val end: Date,
    val paymentType: PaymentType,
    val paymentRecurrence: PaymentRecurrence?,
    val amount: Double,
    val description: String,
    val expenseType: ExpenseType,
    private val operationType: OperationType

) {

    fun isExpense() = operationType == OperationType.EXPENSE

    fun isIncome() = operationType == OperationType.INCOME
}