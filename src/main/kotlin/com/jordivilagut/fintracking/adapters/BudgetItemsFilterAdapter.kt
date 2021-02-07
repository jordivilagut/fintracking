package com.jordivilagut.fintracking.adapters

import com.jordivilagut.fintracking.model.dto.BudgetItemsFilter
import com.jordivilagut.fintracking.services.BudgetItemService
import java.util.*

class BudgetItemsFilterAdapter {

    companion object {
        fun toServiceFilter(filter: BudgetItemsFilter, userId: String): BudgetItemService.Filter {
            return BudgetItemService.Filter.budgetItemsFilter() {
                this.userId = userId
                this.from = Date(filter.from)
                this.to = Date(filter.to)
                this.limit = filter.limit
                this.skip = filter.skip
            }
        }
    }
}