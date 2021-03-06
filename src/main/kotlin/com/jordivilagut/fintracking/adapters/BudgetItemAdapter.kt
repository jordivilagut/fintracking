package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.BudgetItem
import com.jordivilagut.fintracking.model.BudgetTransaction
import com.jordivilagut.fintracking.model.dto.BudgetItemDTO
import com.jordivilagut.fintracking.model.dto.CreateBudgetItemDTO
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence.MONTHLY
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence.WEEKLY
import com.jordivilagut.fintracking.utils.MathUtil.Companion.negative
import com.jordivilagut.fintracking.utils.MathUtil.Companion.round2Dec
import com.jordivilagut.fintracking.utils.MongoUtils
import org.joda.time.DateTime
import org.joda.time.Months
import org.joda.time.Weeks
import java.util.*
import kotlin.math.abs

class BudgetItemAdapter {

    companion object {
        fun toDTO(item: BudgetItem): BudgetItemDTO {
            return BudgetItemDTO(
                id = item.id.toString(),
                start = item.start.time,
                end = item.end?.time,
                amount = round2Dec(abs(item.amount)),
                description = item.description,
                operationType = item.operationType,
                expenseType = item.expenseType,
                paymentType = item.paymentType,
                recurrence = item.recurrence)
        }

        fun toItem(dto: CreateBudgetItemDTO, userId: String): BudgetItem {
            return BudgetItem(
                id = null,
                start = Date(dto.start),
                end = if (dto.end != null) Date(dto.end) else null,
                userId = MongoUtils.toId(userId),
                amount = if (dto.isExpense()) negative(round2Dec(dto.amount)) else round2Dec(dto.amount),
                description = dto.description,
                operationType = dto.operationType,
                expenseType = dto.expenseType,
                paymentType = dto.paymentType,
                recurrence = dto.recurrence)
        }

        fun toBudgetTransactions(item: BudgetItem): List<BudgetTransaction> {
            return if (item.isRecurring())  buildRecurringTransactions(item)
            else listOf(buildSingleTransaction(item, item.start))
        }

        private fun buildRecurringTransactions(item: BudgetItem): List<BudgetTransaction> {

            val start = item.start
            val end = item.end?: DateTime().withDate(DateTime(item.start).year + 1, 1, 1).toDate()
            val recurrence = item.recurrence
            val transactions = arrayListOf<BudgetTransaction>()

            when (recurrence) {
                WEEKLY -> {
                    val weeksBetween = Weeks.weeksBetween(DateTime(start), DateTime(end)).weeks
                    for (week in 0..weeksBetween) {
                        val date = DateTime(item.start).plusWeeks(week).toDate()
                        transactions.add(buildSingleTransaction(item, date))
                    }
                }
                MONTHLY -> {
                    val monthsBetween = Months.monthsBetween(DateTime(start), DateTime(end)).months
                    for (month in 0..monthsBetween) {
                        val date = DateTime(item.start).plusMonths(month).toDate()
                        transactions.add(buildSingleTransaction(item, date))
                    }
                }
                else -> transactions.add(buildSingleTransaction(item, item.start))
            }

            return transactions
        }

        private fun buildSingleTransaction(item: BudgetItem, date: Date) = BudgetTransaction(
            id = null,
            userId = item.userId,
            date = date,
            amount = item.amount,
            description = item.description,
            expenseType = item.expenseType,
            operationType = item.operationType,
            budgetItemId = item.id)
    }
}