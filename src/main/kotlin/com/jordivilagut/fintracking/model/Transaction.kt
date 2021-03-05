package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("transactions")
class Transaction(
        id: ObjectId?,
        val userId: ObjectId,
        val date: Date,
        amount: Double,
        description: String,
        val expenseType: ExpenseType,
        operationType: OperationType,
        val budgetTransactionId: ObjectId?

): BaseTransaction(
        id = id,
        amount = amount,
        description = description,
        operationType = operationType)