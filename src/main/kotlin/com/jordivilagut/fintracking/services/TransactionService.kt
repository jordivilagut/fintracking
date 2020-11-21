package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.Transaction
import org.bson.types.ObjectId
import java.util.function.Consumer

interface TransactionService {

    fun get(id: ObjectId): Transaction?

    fun findAll(): List<Transaction>

    fun findByUserId(userId: ObjectId): List<Transaction>

    fun findByFilter(filter: Filter): List<Transaction>

    fun addTransaction(transaction: Transaction): Transaction

    class Filter : BaseFilter() {

        var userId: String? = null

        companion object {
            /** Convenience method to build [Filter] */
            fun transactionFilter(f: Filter.() -> Unit) = Filter().apply(f)
            /** Convenience method to build [Filter] from Java */
            fun transactionFilter(f: Consumer<Filter>) = Filter().apply { f.accept(this) }
        }
    }
}