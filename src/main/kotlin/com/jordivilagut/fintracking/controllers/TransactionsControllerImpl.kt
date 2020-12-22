package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.adapters.TransactionAdapter
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.TransactionsController.Companion.PATH
import com.jordivilagut.fintracking.model.Transaction
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.services.TransactionService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    override fun addTransaction(): Response<Any> {

        transactionService.addTransaction(Transaction(null, ObjectId(), Date(), 16.20, "test transaction"))

        return Response(null, NO_CONTENT)
    }
}