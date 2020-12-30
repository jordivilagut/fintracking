package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.MonthlySummary

interface FinanceController {

    companion object {
        const val PATH = "finance"
    }

    fun getMonthlySummary(user: User): Response<MonthlySummary>
}