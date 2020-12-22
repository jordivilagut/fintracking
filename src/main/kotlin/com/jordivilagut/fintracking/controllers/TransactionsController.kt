package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.dto.TransactionDTO

interface TransactionsController {

    companion object {
        const val PATH = "transactions"
    }

    fun getTransactions(): Response<List<TransactionDTO>>

    fun addTransaction(): Response<Any>
}