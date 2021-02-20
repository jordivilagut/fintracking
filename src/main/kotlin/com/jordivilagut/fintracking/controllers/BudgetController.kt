package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.BudgetItemDTO
import com.jordivilagut.fintracking.model.dto.BudgetItemsFilter
import com.jordivilagut.fintracking.model.dto.CreateBudgetItemDTO

interface BudgetController {

    companion object {
        const val PATH = "budget"
    }

    fun getBudgetItem(user: User, id: String): Response<BudgetItemDTO>

    fun getBudgetItems(user: User, filter: BudgetItemsFilter): Response<List<BudgetItemDTO>>

    fun addBudgetItem(user: User, dto: CreateBudgetItemDTO): Response<Any>

    fun deleteBudgetItem(user: User, id: String): Response<Any>
}