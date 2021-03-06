package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.BaseTransaction
import com.jordivilagut.fintracking.model.dto.MonthSummary
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.model.dto.Months
import com.jordivilagut.fintracking.model.dto.YearSummary
import com.jordivilagut.fintracking.repositories.balancestatements.BalanceStatementRepository
import com.jordivilagut.fintracking.utils.MathUtil.Companion.round2Dec
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.abs

@Service
class FinanceServiceImpl

    @Autowired
    constructor(
        private val transactionService: TransactionService,
        private val budgetTransactionService: BudgetTransactionService,
        private val balanceStatementRepository: BalanceStatementRepository): FinanceService {

    override fun getYearSummary(userId: String, year: Int): YearSummary {
        val months = Months.values().map {
            //TODO - Months enum start with 1
            val monthlySummary = getMonthlySummary(userId, it.ordinal + 1, year)
            val budgetMonthlySummary = getMonthlyBudgetSummary(userId, it.ordinal + 1, year)
            MonthSummary(it.name.substring(0, 3),
                monthlySummary.income,
                monthlySummary.expenses,
                budgetMonthlySummary.income,
                budgetMonthlySummary.expenses)
        }

        return YearSummary(months)
    }

    private fun getMonthlyBudgetSummary(userId: String, month: Int, year: Int): MonthlySummary {
        val filter = BudgetTransactionService.Filter.transactionFilter {
            this.userId = userId
            this.from = DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(1).withTimeAtStartOfDay().toDate()
            this.to = DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay().toDate()
        }

        val transactions = budgetTransactionService.findByFilter(filter)
        val income = getTotalIncome(transactions)
        val expenses = getTotalExpenses(transactions)

        return MonthlySummary(income, expenses, income - expenses)
    }

    override fun getMonthlySummary(userId: String, month: Int, year: Int): MonthlySummary {
        val filter = TransactionService.Filter.transactionFilter {
            this.userId = userId
            this.from = DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(1).withTimeAtStartOfDay().toDate()
            this.to = DateTime().withYear(year).withMonthOfYear(month).withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay().toDate()
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
        return round2Dec(latestStatement.balance + transactionsSinceLatestStatement.map { it.amount }.sum())
    }

    override fun getLatestStatement(userId: String): BalanceStatement? {
        return balanceStatementRepository.findLatestStatement(userId)
    }

    override fun addLatestBalanceStatement(balanceStatement: BalanceStatement) {
        balanceStatementRepository.save(balanceStatement)
    }

    private fun getTotalIncome(transactions: List<BaseTransaction>): Double {
        return transactions
            .filter { it.isIncome() }
            .map { abs(it.amount) }
            .sum()
    }

    private fun getTotalExpenses(transactions: List<BaseTransaction>): Double {
        return transactions
            .filter { it.isExpense() }
            .map { abs(it.amount) }
            .sum()
    }
}