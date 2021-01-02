package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.dto.MonthlySummary

interface FinanceService {

    fun getMonthlySummary(userId: String): MonthlySummary

    fun getCurrentFunds(userId: String): Double?

    fun getLatestStatement(userId: String): BalanceStatement?

    fun addLatestBalanceStatement(balanceStatement: BalanceStatement)
}