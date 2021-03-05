package com.jordivilagut.fintracking.model

import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("transactions")
class Transaction(
        @Id var id: ObjectId?,
        val userId: ObjectId,
        val date: Date,
        amount: Double,
        description: String,
        val expenseType: ExpenseType,
        operationType: OperationType,
        val budgetTransactionId: ObjectId?

): BaseTransaction(
        amount = amount,
        description = description,
        operationType = operationType)