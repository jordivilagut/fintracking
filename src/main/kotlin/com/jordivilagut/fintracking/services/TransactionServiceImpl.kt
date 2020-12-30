package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.repositories.TransactionRepository
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TransactionServiceImpl

    constructor(
        @Autowired val transactionRepository: TransactionRepository)

    : TransactionService {

    override fun get(id: String): Transaction? {
        return transactionRepository.findById(toId(id)).orElse(null)
    }

    override fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    override fun findByUserId(userId: String): List<Transaction> {
        return transactionRepository.findByUserId(toId(userId))
    }

    override fun findByFilter(filter: TransactionService.Filter): List<Transaction> {
        return transactionRepository.findByQuery(queryFromFilter(filter))
    }

    override fun addTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    override fun deleteTransaction(transactionId: String) {
        val transaction = get(transactionId)?: throw IllegalArgumentException("Transaction not found")
        return transactionRepository.delete(transaction)
    }

    private fun queryFromFilter(filter: TransactionService.Filter): Query {

        val query = Query()

        val userId = filter.userId
        if (userId != null) {
            query.addCriteria(Criteria.where("userId").`is`(toId(userId)))
        }

        query.addCriteria(Criteria.where("date")
            .gte(filter.from)
            .lte(filter.to))

        return query
    }
}