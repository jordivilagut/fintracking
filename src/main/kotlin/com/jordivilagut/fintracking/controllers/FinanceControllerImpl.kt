package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.FinanceController.Companion.PATH
import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.model.dto.YearSummary
import com.jordivilagut.fintracking.services.FinanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(PATH)
class FinanceControllerImpl

    @Autowired
    constructor(
        val financeService: FinanceService)

    : FinanceController {

    @GetMapping("year/{year}/month/{month}")
    override fun getMonthlySummary(
        @AuthenticationPrincipal user: User,
        @PathVariable year: Int,
        @PathVariable month: Int): Response<MonthlySummary> {

        val summary = financeService.getMonthlySummary(user.idStr(), month, year)
        return Response(summary, OK)
    }

    @GetMapping("year/{year}")
    override fun getYearSummary(
        @AuthenticationPrincipal user: User,
        @PathVariable year: Int): Response<YearSummary> {

        val summary = financeService.getYearSummary(user.idStr(), year)
        return Response(summary, OK)
    }

    @GetMapping("balance")
    override fun getBalance(
        @AuthenticationPrincipal user: User): Response<Double?> {

        val balance = financeService.getCurrentFunds(user.idStr())
        return Response(balance, OK)
    }

    @PostMapping("balance")
    override fun setLatestBalanceStatement(
        @AuthenticationPrincipal user: User,
        @RequestBody balance: Double): Response<Any> {

        val balanceStatement = BalanceStatement(null, user.id!!, Date(), balance)
        financeService.addLatestBalanceStatement(balanceStatement)
        return Response(null, NO_CONTENT)
    }
}