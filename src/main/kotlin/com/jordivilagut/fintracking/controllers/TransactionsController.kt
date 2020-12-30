package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.CreateTransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionDTO
import com.jordivilagut.fintracking.model.dto.TransactionsFilter

interface TransactionsController {

    companion object {
        const val PATH = "transactions"
    }

    fun getTransactions(user: User, filter: TransactionsFilter): Response<List<TransactionDTO>>

    fun addTransaction(user: User, dto: CreateTransactionDTO): Response<Any>

    fun deleteTransaction(user: User, transactionId: String): Response<Any>
}