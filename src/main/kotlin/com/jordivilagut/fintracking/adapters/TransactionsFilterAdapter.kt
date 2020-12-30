package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.dto.TransactionsFilter
import com.jordivilagut.fintracking.services.TransactionService

class TransactionsFilterAdapter {

    companion object {
        fun toServiceFilter(filter: TransactionsFilter, userId: String): TransactionService.Filter {
            return TransactionService.Filter.transactionFilter {
                this.userId = userId
                this.limit = filter.limit
                this.skip = filter.skip
            }
        }
    }
}