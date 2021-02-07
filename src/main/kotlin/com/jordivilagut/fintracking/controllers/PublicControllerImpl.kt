package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.PublicController.Companion.PATH
import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
import com.jordivilagut.fintracking.model.dto.PaymentRecurrence
import com.jordivilagut.fintracking.model.dto.PaymentType
import com.jordivilagut.fintracking.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PATH)
class PublicControllerImpl

    @Autowired
    constructor(
        val transactionService: TransactionService)

    : PublicController {

    @GetMapping("operations")
    override fun getOperations(): Response<List<OperationType>> {
        return Response(OperationType.values().toList(), OK)
    }

    @GetMapping("paymenttypes")
    override fun getPaymentTypes(): Response<List<PaymentType>> {
        return Response(PaymentType.values().toList(), OK)
    }

    @GetMapping("expensetypes")
    override fun getExpenseTypes(): Response<List<ExpenseType>> {
        return Response(ExpenseType.values().toList(), OK)
    }

    @GetMapping("recurrencetypes")
    override fun getRecurrenceTypes(): Response<List<PaymentRecurrence>> {
        return Response(PaymentRecurrence.values().toList(), OK)
    }
}

