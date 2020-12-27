package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.adapters.TransactionAdapter
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.TransactionsController.Companion.PATH
import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(PATH)
class TransactionsControllerImpl

    @Autowired
    constructor(
        val transactionService: TransactionService)

    : TransactionsController {

    @GetMapping
    override fun getTransactions(): Response<List<TransactionDTO>> {
        val transactions = transactionService.findAll().map { TransactionAdapter.toDTO(it) }
        return Response(transactions, OK)
    }

    @PostMapping
    override fun addTransaction(
        @AuthenticationPrincipal user: User,
        @RequestBody dto: CreateTransactionDTO): Response<Any> {

        //TODO: validate dto
        val transaction = Transaction(null, user.id!!, Date(), dto.amount, dto.description)
        transactionService.addTransaction(transaction)
        return Response(null, NO_CONTENT)
    }
}