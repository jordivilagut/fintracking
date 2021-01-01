package com.jordivilagut.fintracking.repositories.transactions

import com.jordivilagut.fintracking.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustomTransactionRepositoryImpl

    @Autowired
    constructor(
        val mongoTemplate: MongoTemplate)

    : CustomTransactionRepository {

    companion object {
        val COLLECTION = Transaction::class.java
    }

    override fun findByQuery(query: Query): List<Transaction> {
        return mongoTemplate.find(query, COLLECTION)
    }
}