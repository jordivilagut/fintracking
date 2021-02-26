package com.jordivilagut.fintracking.repositories.budgettransactions

import com.jordivilagut.fintracking.model.BudgetTransaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustemBudgetTransactionRepositoryImpl

    @Autowired
    constructor(
        val mongoTemplate: MongoTemplate)

    : CustemBudgetTransactionRepository {

    companion object {
        val COLLECTION = BudgetTransaction::class.java
    }

    override fun findByQuery(query: Query): List<BudgetTransaction> {
        return mongoTemplate.find(query, COLLECTION)
    }
}