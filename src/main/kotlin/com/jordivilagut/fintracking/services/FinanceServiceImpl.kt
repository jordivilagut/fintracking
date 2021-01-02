package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.repositories.balancestatements.BalanceStatementRepository
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FinanceServiceImpl

    @Autowired
    constructor(
        private val transactionService: TransactionService,
        private val balanceStatementRepository: BalanceStatementRepository)

    : FinanceService {

    override fun getMonthlySummary(userId: String): MonthlySummary {
        val filter = TransactionService.Filter.transactionFilter {
            this.userId = userId
            this.from = DateTime().withDayOfMonth(1).withTimeAtStartOfDay().toDate()
            this.to = DateTime().withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay().toDate()
        }

        val transactions = transactionService.findByFilter(filter)
        val income = getTotalIncome(transactions)
        val expenses = getTotalExpenses(transactions)

        return MonthlySummary(income, expenses, income - expenses)
    }

    override fun getCurrentFunds(userId: String): Double? {
        val latestStatement = getLatestStatement(userId)?: return null
        val filter = TransactionService.Filter.transactionFilter {
            this.userId = userId
            this.from = latestStatement.date
            this.to = Date()
        }

        val transactionsSinceLatestStatement = transactionService.findByFilter(filter)
        return latestStatement.balance + transactionsSinceLatestStatement.map { it.amount }.sum()
    }

    override fun getLatestStatement(userId: String): BalanceStatement? {
        return balanceStatementRepository.findLatestStatement(userId)
    }

    override fun addLatestBalanceStatement(balanceStatement: BalanceStatement) {
        balanceStatementRepository.save(balanceStatement)
    }

    private fun getTotalIncome(transactions: List<Transaction>): Double {
        return transactions
            .filter { it.isIncome() }
            .map { it.amount }
            .sum()
    }

    private fun getTotalExpenses(transactions: List<Transaction>): Double {
        return -transactions
            .filter { it.isExpense() }
            .map { it.amount }
            .sum()
    }
}