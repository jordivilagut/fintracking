package com.jordivilagut.fintracking.repositories.budgetitems

import com.jordivilagut.fintracking.model.BudgetItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustomBudgetItemRepositoryImpl

    @Autowired
    constructor(
        val mongoTemplate: MongoTemplate)

    : CustomBudgetItemRepository {

    companion object {
        val COLLECTION = BudgetItem::class.java
    }

    override fun findByQuery(query: Query): List<BudgetItem> {
        return mongoTemplate.find(query, COLLECTION)
    }
}