package com.jordivilagut.fintracking.repositories.budgettransactions

import com.jordivilagut.fintracking.model.BudgetTransaction
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BudgetTransactionRepository: MongoRepository<BudgetTransaction, ObjectId>, CustemBudgetTransactionRepository {

    fun findByUserId(userId: ObjectId): List<BudgetTransaction>
}