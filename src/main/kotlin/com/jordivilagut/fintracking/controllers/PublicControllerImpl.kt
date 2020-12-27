package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.PublicController.Companion.PATH
import com.jordivilagut.fintracking.model.dto.ExpenseType
import com.jordivilagut.fintracking.model.dto.OperationType
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

    @GetMapping("expensetypes")
    override fun getExpenseTypes(): Response<List<ExpenseType>> {
        //val operations = arrayListOf<DropdownItem>()
        //operations.add(DropdownItem("Clothing", "clothing"))
        //operations.add(DropdownItem("Education", "education"))
        //operations.add(DropdownItem("Transport", "transport"))
        //operations.add(DropdownItem("Telcom", "telcom"))
        //operations.add(DropdownItem("Travels", "travels"))
        //operations.add(DropdownItem("Streaming Platforms", "streaming"))
        //operations.add(DropdownItem("Personal Care", "personalcare"))
        //operations.add(DropdownItem("Groceries", "groceries"))
        //operations.add(DropdownItem("Restaurants", "restaurant"))
        //operations.add(DropdownItem("Healthcare", "healthcare"))
        //operations.add(DropdownItem("Income", "income"))
        //operations.add(DropdownItem("Taxes", "taxes"))
        //operations.add(DropdownItem("Charity", "charity"))
        //operations.add(DropdownItem("Housing", "housing"))
        //operations.add(DropdownItem("Supplies (Electricity, water,...)", "supplies"))
        //operations.add(DropdownItem("Other", "other"))

        return Response(ExpenseType.values().toList(), OK)
    }
}

