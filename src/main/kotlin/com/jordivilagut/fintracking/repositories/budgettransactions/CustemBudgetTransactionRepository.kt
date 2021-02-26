package com.jordivilagut.fintracking.repositories.budgettransactions

import com.jordivilagut.fintracking.model.BudgetTransaction
import org.springframework.data.mongodb.core.query.Query

interface CustemBudgetTransactionRepository {

    fun findByQuery(query: Query): List<BudgetTransaction>
}