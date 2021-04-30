package com.jordivilagut.fintracking.repositories.assets

import com.jordivilagut.fintracking.model.Asset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class CustomAssetRepositoryImpl

    @Autowired
    constructor(
        val mongoTemplate: MongoTemplate)

    : CustomAssetRepository {

    companion object {
        val COLLECTION = Asset::class.java
    }

    override fun findByQuery(query: Query): List<Asset> {
        return mongoTemplate.find(query, COLLECTION)
    }
}