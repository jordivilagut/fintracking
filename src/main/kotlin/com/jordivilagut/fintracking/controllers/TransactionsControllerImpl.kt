package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.adapters.TransactionAdapter.Companion.toDTO
import com.jordivilagut.fintracking.adapters.TransactionAdapter.Companion.toTransaction
import com.jordivilagut.fintracking.adapters.TransactionsFilterAdapter.Companion.toServiceFilter
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.TransactionsController.Companion.PATH
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionsFilter
import com.jordivilagut.fintracking.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PATH)
class TransactionsControllerImpl

    @Autowired
    constructor(
        val transactionService: TransactionService)

    : TransactionsController {

    @GetMapping("{id}")
    override fun getTransaction(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String): Response<TransactionDTO> {

        val item = transactionService.get(id)?: throw IllegalArgumentException("Not found")
        return Response(toDTO(item), OK)
    }

    @PostMapping
    override fun getTransactions(
        @AuthenticationPrincipal user: User,
        @RequestBody filter: TransactionsFilter): Response<List<TransactionDTO>> {

        val transactions = transactionService
            .findByFilter(toServiceFilter(filter, user.idStr()))
            .map { toDTO(it) }

        return Response(transactions, OK)
    }

    @PostMapping("add")
    override fun addTransaction(
        @AuthenticationPrincipal user: User,
        @RequestBody dto: CreateTransactionDTO): Response<Any> {

        //validateTransactionDTO()
        val transaction = toTransaction(dto, user.idStr())
        transactionService.addTransaction(transaction)
        return Response(null, NO_CONTENT)
    }

    @PostMapping("{id}")
    override fun updateTransactions(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String,
        @RequestBody dto: CreateTransactionDTO): Response<Any> {

        //validateTransactionDTO()
        val transaction = toTransaction(dto, user.idStr())
        transactionService.updateTransaction(id, transaction)
        return Response(null, NO_CONTENT)
    }

    @DeleteMapping("{id}")
    override fun deleteTransaction(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String): Response<Any> {

        transactionService.deleteTransaction(id)
        return Response(null, NO_CONTENT)
    }
}