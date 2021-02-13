package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.BudgetItem
import com.jordivilagut.fintracking.model.dto.BudgetItemDTO
import com.jordivilagut.fintracking.model.dto.CreateBudgetItemDTO
import com.jordivilagut.fintracking.utils.MongoUtils
import java.util.*

class BudgetItemAdapter {

    companion object {
        fun toDTO(item: BudgetItem): BudgetItemDTO {
            return BudgetItemDTO(
                id = item.id.toString(),
                start = item.start.time,
                end = item.end?.time,
                amount = item.amount,
                description = item.description,
                expenseType = item.expenseType,
                paymentType = item.paymentType,
                paymentRecurrence = item.paymentRecurrence)
        }

        fun toItem(dto: CreateBudgetItemDTO, userId: String): BudgetItem {
            return BudgetItem(
                id = null,
                start = Date(dto.start),
                end = if (dto.end != null) Date(dto.end) else null,
                userId = MongoUtils.toId(userId),
                amount = if (dto.isExpense()) negative(dto.amount) else dto.amount,
                description = dto.description,
                operationType = dto.operationType,
                expenseType = dto.expenseType,
                paymentType = dto.paymentType,
                paymentRecurrence = dto.paymentRecurrence)
        }

        private fun negative(amount: Double) = 0 - amount
    }
}