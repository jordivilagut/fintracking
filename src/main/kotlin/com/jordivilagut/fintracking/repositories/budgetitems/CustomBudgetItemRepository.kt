package com.jordivilagut.fintracking.repositories.budgetitems

import com.jordivilagut.fintracking.model.BudgetItem
import org.springframework.data.mongodb.core.query.Query

interface CustomBudgetItemRepository {

    fun findByQuery(query: Query): List<BudgetItem>
}