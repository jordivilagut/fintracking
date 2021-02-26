package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.BudgetTransaction
import java.util.*
import java.util.function.Consumer

interface BudgetTransactionService {

    fun get(id: String): BudgetTransaction?

    fun findAll(): List<BudgetTransaction>

    fun findByUserId(userId: String): List<BudgetTransaction>

    fun findByFilter(filter: Filter): List<BudgetTransaction>

    fun addTransaction(transaction: BudgetTransaction): BudgetTransaction

    fun deleteTransaction(id: String)

    class Filter : BaseFilter() {

        var userId: String? = null
        var from: Date = Date()
        var to: Date = Date()

        companion object {
            /** Convenience method to build [Filter] */
            fun transactionFilter(f: Filter.() -> Unit) = Filter().apply(f)
            /** Convenience method to build [Filter] from Java */
            fun transactionFilter(f: Consumer<Filter>) = Filter().apply { f.accept(this) }
        }
    }
}