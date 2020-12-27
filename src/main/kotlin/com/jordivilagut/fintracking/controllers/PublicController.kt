package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType

interface PublicController {

    companion object {
        const val PATH = "public"
    }

    fun getOperations(): Response<List<OperationType>>

    fun getExpenseTypes(): Response<List<ExpenseType>>
}