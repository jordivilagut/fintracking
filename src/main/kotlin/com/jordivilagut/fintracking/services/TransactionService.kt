package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.Transaction
import java.util.*
import java.util.function.Consumer

interface TransactionService {

    fun get(id: String): Transaction?

    fun findAll(): List<Transaction>

    fun findByUserId(userId: String): List<Transaction>

    fun findByFilter(filter: Filter): List<Transaction>

    fun addTransaction(transaction: Transaction): Transaction

    fun updateTransaction(id: String, transaction: Transaction): Transaction

    fun deleteTransaction(transactionId: String)

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