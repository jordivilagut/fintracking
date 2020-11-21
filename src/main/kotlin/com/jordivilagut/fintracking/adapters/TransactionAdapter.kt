package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.dto.TransactionDTO

class TransactionAdapter {

    companion object {
        fun toDTO(transaction: Transaction): TransactionDTO {
            return TransactionDTO(
                    id = transaction.id.toString(),
                    date = transaction.date.time,
                    amount = transaction.amount,
                    description = transaction.description)
        }
    }
}