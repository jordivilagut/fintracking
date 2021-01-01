package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement

interface FinanceService {

    fun getLatestStatement(userId: String): BalanceStatement?

    fun addLatestBalanceStatement(balanceStatement: BalanceStatement)
}