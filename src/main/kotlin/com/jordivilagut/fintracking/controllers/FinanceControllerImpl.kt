package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.FinanceController.Companion.PATH
import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.services.FinanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(PATH)
class FinanceControllerImpl

    @Autowired
    constructor(
        val financeService: FinanceService)

    : FinanceController {

    @GetMapping
    override fun getMonthlySummary(
        @AuthenticationPrincipal user: User): Response<MonthlySummary> {

        val summary = financeService.getMonthlySummary(user.idStr())
        return Response(summary, HttpStatus.OK)
    }

    @GetMapping("balance")
    override fun getBalance(
        @AuthenticationPrincipal user: User): Response<Double?> {

        val balance = financeService.getCurrentFunds(user.idStr())
        return Response(balance, HttpStatus.OK)
    }

    override fun getLatestBalanceStatement(
        @AuthenticationPrincipal user: User): BalanceStatement? {
        return financeService.getLatestStatement(user.idStr())
    }

    override fun setLatestBalanceStatement(
        @AuthenticationPrincipal user: User,
        @RequestBody balance: Double): Response<Any> {

        val balanceStatement = BalanceStatement(null, user.id!!, Date(), balance)
        financeService.addLatestBalanceStatement(balanceStatement)
        return Response(null, NO_CONTENT)
    }
}