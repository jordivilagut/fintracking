package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence
import com.jordivilagut.fintracking.model.dto.PaymentType

interface PublicController {

    companion object {
        const val PATH = "public"
    }

    fun getOperations(): Response<List<OperationType>>

    fun getPaymentTypes(): Response<List<PaymentType>>

    fun getExpenseTypes(): Response<List<ExpenseType>>

    fun getRecurrenceTypes(): Response<List<PaymentRecurrence>>
}