package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.adapters.BudgetItemAdapter
import com.jordivilagut.fintracking.model.BudgetItem
import com.jordivilagut.fintracking.model.BudgetTransaction
import com.jordivilagut.fintracking.repositories.budgettransactions.BudgetTransactionRepository
import com.jordivilagut.fintracking.services.BudgetTransactionService.Filter
import com.jordivilagut.fintracking.services.BudgetTransactionService.Filter.Companion.transactionFilter
import com.jordivilagut.fintracking.utils.Fields.Companion.BUDGET_ITEM_ID
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
        budgetTransactionRepository.delete(transaction)
    }

    override fun deleteBudgetTransactions(itemId: String) {
        val filter = transactionFilter { this.budgetItemId = itemId }
        val transactions = findByFilter(filter)
        budgetTransactionRepository.deleteAll(transactions)
    }

    override fun generateTransactions(item: BudgetItem) {
        val transactions = BudgetItemAdapter.toBudgetTransactions(item)
        budgetTransactionRepository.saveAll(transactions)
    }

    private fun queryFromFilter(filter: Filter): Query {

        val query = Query()

        val userId = filter.userId
        if (userId != null) {
            query.addCriteria(Criteria.where(USER_ID).`is`(toId(userId)))
        }

        val budgetItemId = filter.budgetItemId
        if (budgetItemId != null) {
            query.addCriteria(Criteria.where(BUDGET_ITEM_ID).`is`(toId(budgetItemId)))
        }

        val from = filter.from
        val to = filter.to
        val dateCriteria = Criteria.where("date")
        if (from != null) dateCriteria.gte(from)
        if (to != null) dateCriteria.lte(to)
        if (from != null || to != null) query.addCriteria(dateCriteria)

        return query
    }
}