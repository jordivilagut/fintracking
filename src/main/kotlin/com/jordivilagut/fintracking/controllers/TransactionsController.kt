package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.dto.TransactionDTO

interface TransactionsController {

    fun getTransactions(): Response<List<TransactionDTO>>

    fun addTransaction(): Response<Any>
}