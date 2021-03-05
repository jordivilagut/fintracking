package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence
import com.jordivilagut.fintracking.model.dto.PaymentType
import com.jordivilagut.fintracking.model.dto.PaymentType.RECURRING
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("budgetItems")
class BudgetItem(
    @Id var id: ObjectId?,
    val userId: ObjectId,
    val start: Date,
    val end: Date?,
    val paymentType: PaymentType,
    val recurrence: PaymentRecurrence?,
    val amount: Double,
    val description: String,
    val expenseType: ExpenseType,
    val operationType: OperationType) {

    fun isRecurring() = paymentType == RECURRING

    fun clone() = BudgetItem(
        id = id,
        userId = userId,
        start = start,
        end = end,
        paymentType = paymentType,
        recurrence = recurrence,
        amount = amount,
        description = description,
        expenseType = expenseType,
        operationType = operationType)
}