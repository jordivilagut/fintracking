package com.jordivilagut.fintracking.repositories

import com.jordivilagut.fintracking.model.Transaction
import org.springframework.data.mongodb.core.query.Query

interface CustomTransactionRepository {

    fun findByQuery(query: Query): List<Transaction>
}