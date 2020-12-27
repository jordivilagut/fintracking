package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.repositories.TransactionRepository
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
        TODO("Not yet implemented")
    }

    override fun addTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }
}