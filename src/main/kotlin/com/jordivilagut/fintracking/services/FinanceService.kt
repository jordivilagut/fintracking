package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.model.dto.YearSummary

interface FinanceService {

    fun getYearSummary(userId: String, year: Int): YearSummary

    fun getMonthlySummary(userId: String, month: Int, year: Int): MonthlySummary

    fun getCurrentFunds(userId: String): Double?

    fun getLatestStatement(userId: String): BalanceStatement?

    fun addLatestBalanceStatement(balanceStatement: BalanceStatement)
}