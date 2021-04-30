package com.jordivilagut.fintracking.repositories.assets

import com.jordivilagut.fintracking.model.Asset
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface AssetRepository: MongoRepository<Asset, ObjectId>, CustomAssetRepository {

    fun findByUserId(userId: ObjectId): List<Asset>
}