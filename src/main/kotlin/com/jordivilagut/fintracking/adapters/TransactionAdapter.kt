package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import java.util.*

class TransactionAdapter {

    companion object {
        fun toDTO(transaction: Transaction): TransactionDTO {
            return TransactionDTO(
                    id = transaction.id.toString(),
                    date = transaction.date.time,
                    amount = transaction.amount,
                    description = transaction.description)
        }

        fun toTransaction(dto: CreateTransactionDTO, userId: String): Transaction {
            return Transaction(
                id = null,
                date = Date(), //Use UTC
                userId = toId(userId),
                amount = dto.amount,
                description = dto.description)
        }
    }
}