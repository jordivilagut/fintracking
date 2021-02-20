package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.BudgetItem
import com.jordivilagut.fintracking.repositories.budgetitems.BudgetItemRepository
import com.jordivilagut.fintracking.utils.Fields.Companion.USER_ID
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class BudgetItemServiceImpl

    @Autowired
    constructor(
        val budgetItemRepository: BudgetItemRepository
    ): BudgetItemService {

    override fun get(id: String): BudgetItem? {
        return budgetItemRepository.findById(toId(id)).orElse(null)
    }

    override fun findAll(): List<BudgetItem> {
        return budgetItemRepository.findAll()
    }

    override fun findByUserId(userId: String): List<BudgetItem> {
        return budgetItemRepository.findByUserId(toId(userId))
    }

    override fun findByFilter(filter: BudgetItemService.Filter): List<BudgetItem> {
        return budgetItemRepository.findByQuery(queryFromFilter(filter))
    }

    override fun addBudgetItem(item: BudgetItem): BudgetItem {
        return budgetItemRepository.save(item)
    }

    override fun updateBudgetItem(id: String, item: BudgetItem): BudgetItem {
        item.id = toId(id)
        return budgetItemRepository.save(item)
    }

    override fun deleteBudgetItem(itemId: String) {
        val item = get(itemId)?: throw IllegalArgumentException("Budget Item not found")
        return budgetItemRepository.delete(item)
    }

    private fun queryFromFilter(filter: BudgetItemService.Filter): Query {

        val query = Query()

        val userId = filter.userId
        if (userId != null) {
            query.addCriteria(Criteria.where(USER_ID).`is`(toId(userId)))
        }

        query.addCriteria(Criteria.where("start").gte(filter.from).lte(filter.to))
        //query.addCriteria(Criteria.where("end").gte(filter.from).lte(filter.to))

        return query
    }
}