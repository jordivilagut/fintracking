package com.jordivilagut.fintracking.repositories.balancestatements

import com.jordivilagut.fintracking.model.BalanceStatement
import com.jordivilagut.fintracking.utils.Fields.Companion.DATE
import com.jordivilagut.fintracking.utils.Fields.Companion.USER_ID
import com.jordivilagut.fintracking.utils.MongoUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustomBalanceStatementRepositoryImpl

    constructor(
        @Autowired val mongoTemplate: MongoTemplate)

    : CustomBalanceStatementRepository {

    companion object {
        val COLLECTION = BalanceStatement::class.java
    }

    override fun findLatestStatement(userId: String): BalanceStatement? {
        val query = Query()
        query.addCriteria(Criteria.where(USER_ID).`is`(MongoUtils.toId(userId)))
        query.with(Sort.by(DESC, DATE))

        return mongoTemplate.findOne(query, COLLECTION)
    }
}