package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.repositories.balancestatements.BalanceStatementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FinanceServiceImpl

    @Autowired
    constructor(
        private val balanceStatementRepository: BalanceStatementRepository)

    : FinanceService {

    override fun getLatestStatement(userId: String): BalanceStatement? {
        return balanceStatementRepository.findLatestStatement(userId)
    }

    override fun addLatestBalanceStatement(balanceStatement: BalanceStatement) {
        balanceStatementRepository.save(balanceStatement)
    }
}