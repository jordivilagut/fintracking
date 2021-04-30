package com.jordivilagut.fintracking.repositories.assets

import com.jordivilagut.fintracking.model.Asset
import org.springframework.data.mongodb.core.query.Query

interface CustomAssetRepository {

    fun findByQuery(query: Query): List<Asset>
}