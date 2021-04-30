package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.model.Asset
import com.jordivilagut.fintracking.repositories.assets.AssetRepository
import com.jordivilagut.fintracking.services.AssetsService.Filter
import com.jordivilagut.fintracking.utils.Fields
import com.jordivilagut.fintracking.utils.MongoUtils.Companion.toId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class AssetsServiceImpl

    @Autowired
    constructor(
        val repository: AssetRepository
    )

    : AssetsService {

    override fun get(id: String): Asset? {
        return repository.findById(toId(id)).orElse(null)
    }

    override fun findAll(): List<Asset> {
        return repository.findAll()
    }

    override fun findByUserId(userId: String): List<Asset> {
        return repository.findByUserId(toId(userId))
    }

    override fun findByFilter(filter: Filter): List<Asset> {
        return repository.findByQuery(queryFromFilter(filter))
    }

    override fun addAsset(asset: Asset): Asset {
        return repository.save(asset)
    }

    override fun updateAsset(id: String, asset: Asset): Asset {
        asset.id = toId(id)
        return repository.save(asset)
    }

    override fun deleteAsset(id: String) {
        val asset = get(id)?: throw IllegalArgumentException("Asset not found")
        return repository.delete(asset)
    }

    private fun queryFromFilter(filter: Filter): Query {

        val query = Query()

        val userId = filter.userId
        if (userId != null) {
            query.addCriteria(Criteria.where(Fields.USER_ID).`is`(toId(userId)))
        }

        return query
    }
}