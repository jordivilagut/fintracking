package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.FinanceController.Companion.PATH
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.MonthlySummary
import com.jordivilagut.fintracking.services.TransactionService
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PATH)
class FinanceControllerImpl

    @Autowired
    constructor(
        val transactionService: TransactionService)

    : FinanceController {

    @GetMapping
    override fun getMonthlySummary(
        @AuthenticationPrincipal user: User): Response<MonthlySummary> {

        //TODO - Move to service
        val filter = TransactionService.Filter.transactionFilter {
            this.userId = user.idStr()
            this.from = DateTime().withDayOfMonth(1).withTimeAtStartOfDay().toDate()
            this.to = DateTime().withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay().toDate()
        }

        val transactions = transactionService.findByFilter(filter)
        val income = transactions.filter { it.isIncome() }.map { it.amount }.sum()
        val expenses = -transactions.filter { it.isExpense() }.map { it.amount }.sum()
        val summary = MonthlySummary(income, expenses, income - expenses)

        return Response(summary, HttpStatus.OK)
    }
}