package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO

interface TransactionsController {

    companion object {
        const val PATH = "transactions"
    }

    fun getTransactions(): Response<List<TransactionDTO>>

    fun addTransaction(user: User, dto: CreateTransactionDTO): Response<Any>
}