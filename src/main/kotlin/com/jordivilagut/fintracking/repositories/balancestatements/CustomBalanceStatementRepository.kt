package com.jordivilagut.fintracking.repositories.balancestatements

import com.jordivilagut.fintracking.model.BalanceStatement

interface CustomBalanceStatementRepository {

    fun findLatestStatement(userId: String): BalanceStatement?
}