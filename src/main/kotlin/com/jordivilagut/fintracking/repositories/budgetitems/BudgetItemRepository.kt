package com.jordivilagut.fintracking.repositories.budgetitems

import com.jordivilagut.fintracking.model.BudgetItem
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BudgetItemRepository: MongoRepository<BudgetItem, ObjectId>, CustomBudgetItemRepository {

    fun findByUserId(userId: ObjectId): List<BudgetItem>
}