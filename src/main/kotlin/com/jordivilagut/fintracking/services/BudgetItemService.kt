package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.BudgetItem
import java.util.*
import java.util.function.Consumer

interface BudgetItemService {

    fun get(id: String): BudgetItem?

    fun findAll(): List<BudgetItem>

    fun findByUserId(userId: String): List<BudgetItem>

    fun findByFilter(filter: Filter): List<BudgetItem>

    fun addBudgetItem(item: BudgetItem): BudgetItem

    fun updateBudgetItem(id: String, item: BudgetItem): BudgetItem

    fun deleteBudgetItem(itemId: String)

    class Filter : BaseFilter() {

        var userId: String? = null
        var from: Date = Date()
        var to: Date = Date()

        companion object {
            /** Convenience method to build [Filter] */
            fun budgetItemsFilter(f: Filter.() -> Unit) = Filter().apply(f)
            /** Convenience method to build [Filter] from Java */
            fun budgetItemsFilter(f: Consumer<Filter>) = Filter().apply { f.accept(this) }
        }
    }
}