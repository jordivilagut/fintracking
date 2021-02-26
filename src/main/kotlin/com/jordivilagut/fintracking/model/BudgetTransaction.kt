package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.OperationType.EXPENSE
import com.jordivilagut.fintracking.model.dto.OperationType.INCOME
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("budgetTransactions")
class BudgetTransaction(
    @Id var id: ObjectId?,
    val userId: ObjectId,
    val date: Date,
    val amount: Double,
    val description: String,
    val expenseType: ExpenseType,
    val operationType: OperationType,
    val budgetItemId: ObjectId?) {

    fun isExpense() = operationType == EXPENSE

    fun isIncome() = operationType == INCOME
}