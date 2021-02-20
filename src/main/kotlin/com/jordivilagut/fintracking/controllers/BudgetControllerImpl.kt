package com.jordivilagut.fintracking.controllers

import com.jordivilagut.fintracking.adapters.BudgetItemAdapter
import com.jordivilagut.fintracking.adapters.BudgetItemAdapter.Companion.toDTO
import com.jordivilagut.fintracking.adapters.BudgetItemsFilterAdapter
import com.jordivilagut.fintracking.base.Response
import com.jordivilagut.fintracking.controllers.BudgetController.Companion.PATH
import com.jordivilagut.fintracking.model.User
import com.jordivilagut.fintracking.model.dto.BudgetItemDTO
import com.jordivilagut.fintracking.model.dto.BudgetItemsFilter
import com.jordivilagut.fintracking.model.dto.CreateBudgetItemDTO
import com.jordivilagut.fintracking.services.BudgetItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PATH)
class BudgetControllerImpl

    @Autowired
    constructor(
        val budgetItemService: BudgetItemService
    ): BudgetController {

    @GetMapping("{id}")
    override fun getBudgetItem(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String): Response<BudgetItemDTO> {

        val item = budgetItemService.get(id)?: throw IllegalArgumentException("Not found")
        return Response(toDTO(item), OK)
    }

    @PostMapping
    override fun getBudgetItems(
        @AuthenticationPrincipal user: User,
        @RequestBody filter: BudgetItemsFilter): Response<List<BudgetItemDTO>> {

        val items = budgetItemService
            .findByFilter(BudgetItemsFilterAdapter.toServiceFilter(filter, user.idStr()))
            .map { toDTO(it) }

        return Response(items, OK)
    }

    @PostMapping("add")
    override fun addBudgetItem(
        @AuthenticationPrincipal user: User,
        @RequestBody dto: CreateBudgetItemDTO): Response<Any> {

        //validateItem()
        val item = BudgetItemAdapter.toItem(dto, user.idStr())
        budgetItemService.addBudgetItem(item)
        return Response(null, NO_CONTENT)
    }

    @DeleteMapping("{id}")
    override fun deleteBudgetItem(
        @AuthenticationPrincipal user: User,
        @PathVariable id: String): Response<Any> {

        budgetItemService.deleteBudgetItem(id)
        return Response(null, NO_CONTENT)
    }
}