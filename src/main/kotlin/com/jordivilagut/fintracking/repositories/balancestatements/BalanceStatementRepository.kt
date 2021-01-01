package com.jordivilagut.fintracking.repositories.balancestatements

import com.jordivilagut.fintracking.model.BalanceStatement
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BalanceStatementRepository: MongoRepository<BalanceStatement, ObjectId>, CustomBalanceStatementRepository {

    fun findByUserId(userId: ObjectId): List<BalanceStatement>
}