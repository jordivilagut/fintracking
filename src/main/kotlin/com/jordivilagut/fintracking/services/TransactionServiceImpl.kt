package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.repositories.TransactionRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl

    constructor(
        @Autowired val transactionRepository: TransactionRepository)

    : TransactionService {

    override fun get(id: ObjectId): Transaction? {
        return transactionRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    override fun findByUserId(userId: ObjectId): List<Transaction> {
        return transactionRepository.findByUserId(userId)
    }

    override fun findByFilter(filter: TransactionService.Filter): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun addTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }
}