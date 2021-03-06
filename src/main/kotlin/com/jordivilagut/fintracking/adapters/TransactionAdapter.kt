package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.utils.MathUtil
import com.jordivilagut.fintracking.utils.MathUtil.Companion.negative
import com.jordivilagut.fintracking.utils.MathUtil.Companion.round2Dec
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import java.util.*
import kotlin.math.abs

class TransactionAdapter {

    companion object {
        fun toDTO(transaction: Transaction): TransactionDTO {
            return TransactionDTO(
                id = transaction.id.toString(),
                date = transaction.date,
                amount = round2Dec(abs(transaction.amount)),
                description = transaction.description,
                operationType = transaction.operationType,
                expenseType = transaction.expenseType)
        }

        fun toTransaction(dto: CreateTransactionDTO, userId: String): Transaction {
            return Transaction(
                id = null,
                date = Date(), //Use UTC
                userId = toId(userId),
                amount = if (dto.isExpense()) negative(round2Dec(dto.amount)) else round2Dec(dto.amount),
                description = dto.description,
                operationType = dto.operationType,
                expenseType = dto.expenseType,
                budgetTransactionId = null)
        }
    }
}