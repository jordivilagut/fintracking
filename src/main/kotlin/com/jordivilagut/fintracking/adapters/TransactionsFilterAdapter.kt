package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.dto.TransactionsFilter
import com.jordivilagut.fintracking.services.TransactionService
import java.util.*

class TransactionsFilterAdapter {

    companion object {
        fun toServiceFilter(filter: TransactionsFilter, userId: String): TransactionService.Filter {
            return TransactionService.Filter.transactionFilter {
                this.userId = userId
                this.from = Date(filter.from)
                this.to = Date(filter.to)
                this.limit = filter.limit
                this.skip = filter.skip
            }
        }
    }
}