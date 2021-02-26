package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BudgetTransaction
import com.jordivilagut.fintracking.repositories.budgettransactions.BudgetTransactionRepository
import com.jordivilagut.fintracking.services.BudgetTransactionService.Filter
import com.jordivilagut.fintracking.utils.Fields.Companion.USER_ID
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class BudgetTransactionServiceImpl

    @Autowired
    constructor(
        val budgetTransactionRepository: BudgetTransactionRepository): BudgetTransactionService {

    override fun get(id: String): BudgetTransaction? {
        return budgetTransactionRepository.findById(toId(id)).orElse(null)
    }

    override fun findAll(): List<BudgetTransaction> {
        return budgetTransactionRepository.findAll()
    }

    override fun findByUserId(userId: String): List<BudgetTransaction> {
        return budgetTransactionRepository.findByUserId(toId(userId))
    }

    override fun findByFilter(filter: Filter): List<BudgetTransaction> {
        return budgetTransactionRepository.findByQuery(queryFromFilter(filter))
    }

    override fun addTransaction(transaction: BudgetTransaction): BudgetTransaction {
        return budgetTransactionRepository.save(transaction)
    }

    override fun deleteTransaction(id: String) {
        val transaction = get(id)?: throw IllegalArgumentException("Transaction not found")
        return budgetTransactionRepository.delete(transaction)
    }

    private fun queryFromFilter(filter: Filter): Query {

        val query = Query()

        val userId = filter.userId
        if (userId != null) {
            query.addCriteria(Criteria.where(USER_ID).`is`(toId(userId)))
        }

        query.addCriteria(Criteria.where("date")
            .gte(filter.from)
            .lte(filter.to))

        return query
    }
}