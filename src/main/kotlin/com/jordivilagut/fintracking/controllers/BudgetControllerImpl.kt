package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.adapters.BudgetItemAdapter
import com.jordivilagut.fintracking.adapters.BudgetItemsFilterAdapter
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.BudgetController.Companion.PATH
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.BudgetItemDTO
import com.jordivilagut.fintracking.model.dto.BudgetItemsFilter
import com.jordivilagut.fintracking.model.dto.CreateBudgetItemDTO
import com.jordivilagut.fintracking.services.BudgetItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PATH)
class BudgetControllerImpl

    @Autowired
    constructor(
        val budgetItemService: BudgetItemService
    ): BudgetController {

    @PostMapping
    override fun getBudgetItems(
        @AuthenticationPrincipal user: User,
        @RequestBody filter: BudgetItemsFilter): Response<List<BudgetItemDTO>> {

        val items = budgetItemService
            .findByFilter(BudgetItemsFilterAdapter.toServiceFilter(filter, user.idStr()))
            .map { BudgetItemAdapter.toDTO(it) }

        return Response(items, HttpStatus.OK)
    }

    @PostMapping("add")
    override fun addBudgetItem(
        @AuthenticationPrincipal user: User,
        @RequestBody dto: CreateBudgetItemDTO): Response<Any> {

        //validateItem()
        val item = BudgetItemAdapter.toItem(dto, user.idStr())
        budgetItemService.addBudgetItem(item)
        return Response(null, HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("{itemId}")
    override fun deleteBudgetItem(
        @AuthenticationPrincipal user: User,
        @PathVariable itemId: String): Response<Any> {

        budgetItemService.deleteBudgetItem(itemId)
        return Response(null, HttpStatus.NO_CONTENT)
    }
}