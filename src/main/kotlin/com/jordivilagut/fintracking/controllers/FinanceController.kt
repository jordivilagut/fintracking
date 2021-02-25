package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.model.dto.YearSummary

interface FinanceController {

    companion object {
        const val PATH = "finance"
    }

    fun getYearSummary(user: User, year: Int): Response<YearSummary>

    fun getMonthlySummary(user: User, year: Int, month: Int): Response<MonthlySummary>

    fun getBalance(user: User): Response<Double?>

    fun setLatestBalanceStatement(user: User, balance: Double): Response<Any>
}