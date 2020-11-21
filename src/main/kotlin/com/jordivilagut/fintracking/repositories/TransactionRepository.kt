package com.jordivilagut.fintracking.repositories

import com.jordivilagut.fintracking.model.Transaction
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TransactionRepository: MongoRepository<Transaction, ObjectId> {

    fun findByUserId(userId: ObjectId): List<Transaction>
}